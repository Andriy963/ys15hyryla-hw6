package ua.shad.pizzaservice.infrastruct;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrii
 */
public class JavaConfigApplicationContext implements ApplicationContext {

    private Config config;
    private Map<String, Object> beans = new HashMap<>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        Object bean = beans.get(beanName);
        if (bean != null) {
            return bean;
        }

        //bean = createBean(beanName);

        BeanBuilder beanBuilder = new BeanBuilder(beanName);
        beanBuilder.createBean();
        beanBuilder.callInitMethod();
        beanBuilder.createProxy();        
        bean = beanBuilder.build();

        beans.put(beanName, bean);
        return bean;
    }

    private class BeanBuilder {

        private Object bean;        
        private final Class<?> type;

        private BeanBuilder(String beanName) {            
            type = config.getImplementation(beanName);
        }

        private void createProxy() {
            bean = new ProxyForBenchmark(bean).createProxy();
        }

        private void callInitMethod() throws Exception {
            try {
                Method method = type.getMethod("init");
                method.invoke(bean);                
            } catch (NoSuchMethodException ex) {
                return;
            }
               
        }

        private Object build() {
            return bean;
        }

        private void createBean() throws Exception {           
            Constructor constructor = type.getConstructors()[0];
            if (constructor.getParameterCount() == 0) {
                bean = type.newInstance();
            } else {
                Object[] params = getParams(constructor);
                bean = constructor.newInstance(params);
            }            
        }

        private Object[] getParams(Constructor constructor) throws Exception {
            Object[] params = new Object[constructor.getParameterCount()];
            Class[] paramTypes = constructor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                Object param = getBeanByType(paramTypes[i]);
                params[i] = param;
            }
            return params;
        }

        private Object getBeanByType(Class paramType) throws Exception {
            String beanName = getBeanNameByType(paramType);
            return getBean(beanName);
        }

        private String getBeanNameByType(Class beanType) {
            String typeName = beanType.getSimpleName();
            String beanName = convertFirstCharacterToLowerCase(typeName);
            return beanName;
        }

        private String convertFirstCharacterToLowerCase(String input) {
            String out
                    = Character.toLowerCase(input.charAt(0))
                    + input.substring(1);
            return out;
        }

    }

}

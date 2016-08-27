package ua.shad.pizzaservice.infrastruct;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author andrii
 */
class ProxyForBenchmark {

    private Object bean;

    public ProxyForBenchmark(Object bean) {
        this.bean = bean;
    }

    public Object createProxy() {
        Object proxedBean
                = Proxy.newProxyInstance(
                        ClassLoader.getSystemClassLoader(),
                        bean.getClass().getInterfaces(),
                        handler);
        return proxedBean;
    }

    InvocationHandler handler = new InvocationHandler() {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Benchmark annotation = bean.getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(Benchmark.class);

            if ((annotation != null) && annotation.active()) {
                long start = System.nanoTime();
                Object result = method.invoke(bean, args);
                long end = System.nanoTime();
                System.out.println("benchmark for " + method.getName() + ": " + (end - start));
                return result;
            } else {
                return method.invoke(bean, args);
            }
        }

    };

}

package ua.shad.pizzaservice.infrastruct;

/**
 *
 * @author andrii
 */
public class ObjectFactory implements ServiceLocator {
    
    private Config config;

    public ObjectFactory(Config config) {
        this.config = config;
    }    
    
    @Override
    public Object lookup(String beanName) throws Exception {
        Class<?> clazz = config.getImplementation(beanName);
        if (clazz != null) {
            return clazz.newInstance();
        }
        return null;
    }
    
}

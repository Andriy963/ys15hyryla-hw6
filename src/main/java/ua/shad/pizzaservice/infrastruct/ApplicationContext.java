package ua.shad.pizzaservice.infrastruct;

/**
 *
 * @author andrii
 */
public interface ApplicationContext {
    Object getBean(String beanName) throws Exception;
}

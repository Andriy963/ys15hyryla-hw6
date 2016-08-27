package ua.shad.pizzaservice.infrastruct;

/**
 *
 * @author andrii
 */
public interface ServiceLocator {
    Object lookup(String beanName) throws Exception;
}

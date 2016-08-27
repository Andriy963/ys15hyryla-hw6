package ua.shad.pizzaservice.infrastruct;

/**
 *
 * @author andrii
 */
public interface Config {
    Class<?> getImplementation(String beanName);
}

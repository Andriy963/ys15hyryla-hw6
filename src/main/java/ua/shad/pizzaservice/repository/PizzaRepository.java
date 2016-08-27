package ua.shad.pizzaservice.repository;

import java.util.List;
import ua.shad.pizzaservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaRepository {
    Pizza getPizzaById(Integer id);
    
    public List<Pizza> getPizzas();
}

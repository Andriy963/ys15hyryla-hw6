package ua.shad.pizzaservice.service;

import java.util.List;
import ua.shad.pizzaservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaService {
    Pizza getPizzaById(Integer id);
    
    public List<Pizza> getPizzas();
}

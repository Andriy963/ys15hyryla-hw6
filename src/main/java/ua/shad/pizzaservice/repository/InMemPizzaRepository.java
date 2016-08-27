package ua.shad.pizzaservice.repository;

import java.util.ArrayList;
import java.util.List;
import ua.shad.pizzaservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public class InMemPizzaRepository implements PizzaRepository {
    
    private List<Pizza> pizzas = new ArrayList<>();

    @Override
    public Pizza getPizzaById(Integer id) {
        return pizzas.get(id-1);
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }    
    
    public void init() {
        //pizzas.add(new Pizza(1, "Pizza 1", 102.5, Pizza.PizzaType.SEA));
        //pizzas.add(new Pizza(2, "Pizza 2", 83.4, Pizza.PizzaType.VEGAN));
        //pizzas.add(new Pizza(3, "Pizza 3", 183.4, Pizza.PizzaType.MEAT));
    }
    
}

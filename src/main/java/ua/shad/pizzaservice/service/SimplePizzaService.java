package ua.shad.pizzaservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shad.pizzaservice.domain.Pizza;
import ua.shad.pizzaservice.repository.PizzaRepository;

/**
 *
 * @author andrii
 */
@Service
public class SimplePizzaService implements PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public Pizza getPizzaById(Integer id) {
        
        Pizza pizza = null;
        try {
            pizza = pizzaRepository.getPizzaById(id);
        } catch (RuntimeException e) {
            return null;
        }
                
        return pizza;
    }

    @Override
    public List<Pizza> getPizzas() {
        return pizzaRepository.getPizzas();
    }
    
}

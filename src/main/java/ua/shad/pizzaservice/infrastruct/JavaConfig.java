package ua.shad.pizzaservice.infrastruct;

import java.util.HashMap;
import java.util.Map;
import ua.shad.pizzaservice.repository.InMemOrderRepository;
import ua.shad.pizzaservice.repository.InMemPizzaRepository;
import ua.shad.pizzaservice.service.SimpleOrderService;

/**
 *
 * @author andrii
 */
public class JavaConfig implements Config {
    
    private Map<String, Class<?>> beans = new HashMap<>();
    {
        beans.put("orderRepository", InMemOrderRepository.class);
        beans.put("pizzaRepository", InMemPizzaRepository.class);
        beans.put("orderService", SimpleOrderService.class);
    }

    @Override
    public Class<?> getImplementation(String beanName) {
        return beans.get(beanName);
    }
    
}

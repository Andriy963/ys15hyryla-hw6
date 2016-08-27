package ua.shad.pizzaservice.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import ua.shad.pizzaservice.domain.Order;

/**
 *
 * @author andrii
 */
@Repository
public class InMemOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();
    
    @Override
    public Integer saveOrder(Order order) {
        orders.add(order);
        return order.getId();
    }
    
    @Override
    public Order getOrder(Integer id) {
        return orders.get(id);
    }
    
}

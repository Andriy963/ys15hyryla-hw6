package ua.shad.pizzaservice.repository;

import ua.shad.pizzaservice.domain.Order;

/**
 *
 * @author andrii
 */
public interface OrderRepository {
    Integer saveOrder(Order order);
    Order getOrder(Integer id);
}

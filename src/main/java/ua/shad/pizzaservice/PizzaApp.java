package ua.shad.pizzaservice;

import ua.shad.pizzaservice.service.OrderService;
import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.domain.Customer;
import ua.shad.pizzaservice.infrastruct.ApplicationContext;
import ua.shad.pizzaservice.infrastruct.Config;
import ua.shad.pizzaservice.infrastruct.JavaConfig;
import ua.shad.pizzaservice.infrastruct.JavaConfigApplicationContext;
import ua.shad.pizzaservice.infrastruct.ObjectFactory;
import ua.shad.pizzaservice.infrastruct.ServiceLocator;
import ua.shad.pizzaservice.repository.PizzaRepository;

/**
 *
 * @author andrii
 */
public class PizzaApp {
    public static ServiceLocator locator;
    
    public static void main(String[] args) throws Exception {
        Config config = new JavaConfig();
        ApplicationContext context = new JavaConfigApplicationContext(config);
        
        Customer customer = new Customer(1, "Andrii");        
        Order order;
        
        PizzaRepository pizzaRepozitory = 
                 (PizzaRepository) context.getBean("pizzaRepository");
        System.out.println(pizzaRepozitory.getClass());
        
        System.out.println(pizzaRepozitory.getPizzaById(1));
        
        OrderService orderService = 
                (OrderService) context.getBean("orderService");
        //        new SimpleOrderService(pizzaRepozitory, orderRepository);
        order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(orderService.getClass());
        
        
        System.out.println(order);
        System.out.println("Price: " + order.getTotalPrice());
    }
}
 
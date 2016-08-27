package ua.shad.pizzaservice.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.shad.pizzaservice.domain.Customer;
import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.domain.Pizza;
import ua.shad.pizzaservice.service.OrderService;

/**
 *
 * @author andrii hyryla
 */
@RestController
public class OrderRestController {
    @Autowired
    private final OrderService orderService;
    
    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @RequestMapping(value = "order/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable("id")int id) {
        if (id < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Order order = orderService.getOrderById(id);
        
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }
    @RequestMapping(value = "order/{id}/item/", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> getItemsInOrder(@PathVariable("id") Integer id) {
        if (id < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order.getPizzas(), HttpStatus.FOUND);
    }
    
    @RequestMapping(value = "order/{orderId}/item/{pizzaId}", method = RequestMethod.PUT)
    public ResponseEntity addItemToOrder(@PathVariable("orderId")int orderId,
                                         @PathVariable("pizzaId")int pizzaId) {

        if (orderId < 0 || pizzaId < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.addPizzaToOrder(orderService.getOrderById(pizzaId), pizzaId);
        return new ResponseEntity(HttpStatus.FOUND);
    }
    
    @RequestMapping(value = "order/{orderId}/item/{pizzaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteItemFromOrder(@PathVariable("orderId") int orderId,
                                                       @PathVariable("pizzaId") int pizzaId) {
        if (orderId < 0 || pizzaId < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Order order = orderService.removePizzaFromOrder(orderService.getOrderById(pizzaId), pizzaId);
        if (order == null) {
            return new ResponseEntity<>(orderId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "order/{orderId}/customer", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateCustomerInOrder(@PathVariable("orderId") int orderId,
                                                @RequestBody Customer customer) {
        if (orderId < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setCustomer(customer);
        
        
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

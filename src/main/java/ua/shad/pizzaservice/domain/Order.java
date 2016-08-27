package ua.shad.pizzaservice.domain;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author andrii
 */
@Component
@Scope(scopeName = "prototype")
public class Order {
    private static int count;
    
    private Integer id;
    private Customer customer;
    private List<Pizza> pizzas;
    //private double totalPrice;
    
    public Order() {
        id = ++count;        
    }
    
    public Order(Customer customer, List<Pizza> pizzas) {
        this();
        this.customer = customer;
        this.pizzas = pizzas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    
    public double getTotalPrice() {
        return pizzas.stream().mapToDouble(Pizza::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", pizzas=" + pizzas + '}';
    }
    
    
}

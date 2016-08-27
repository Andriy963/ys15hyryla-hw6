package ua.shad.pizzaservice.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.domain.Pizza;
import ua.shad.pizzaservice.service.OrderService;
import ua.shad.pizzaservice.service.PizzaService;

public class PizzaServlet extends HttpServlet {

    private ConfigurableApplicationContext repositoryContext;
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        String parentContextsConfigLocation = 
                this.getServletContext().getInitParameter("contextConfigLocation");
        String[] configs = parentContextsConfigLocation.split(" ");
        System.out.println(configs);
        String webContextConfigLocation = this.getInitParameter("contextConfigLocation");
        System.out.println(webContextConfigLocation);       
        
        repositoryContext = new ClassPathXmlApplicationContext(configs[0]);
        context = new ClassPathXmlApplicationContext(
                new String[]{configs[1]}, false);
        context.setParent(repositoryContext);
        context.refresh();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello from Pizza Service </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Servlet PizzaServlet at " + request.getRequestURI());
            out.println("<br>");
            out.println("Params: " + request.getParameter("pizzas"));
            out.println("<br>");

            PizzaService pizzaService = context.getBean(PizzaService.class);
            List<Pizza> pizzas = pizzaService.getPizzas();

            for (Pizza pizza : pizzas) {
                out.println(pizza);
                out.println("<br>");
            }

            OrderService orderService = context.getBean(OrderService.class);

            String pizzasArr = request.getParameter("pizzas");
            if (pizzasArr != null) {
                Integer[] pizzasID = parseStringToArray(pizzasArr);
                Order order = orderService.placeNewOrder(null, pizzasID);
                out.println("Pizzas in order: " + order.getPizzas());
                out.println("<br>");
                out.println("Tolat price: " + order.getTotalPrice());
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    private Integer[] parseStringToArray(String arr) {
        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        Integer[] results = new Integer[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            };
        }
        return results;
    }

    @Override
    public void destroy() {
        context.close();
        repositoryContext.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

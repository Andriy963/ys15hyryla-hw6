package ua.shad.pizzaservice.web.infrastructure;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andrii
 */
public class URLHandlerMapping {
    private static Map<String, String> urlHandlerMapping = new HashMap<>();
    {
        urlHandlerMapping.put("/pizzas", "allPizzasContoller");
    }
    
    public static String getControllerBeanName(String url) {
        return urlHandlerMapping.get(url);
    }
}

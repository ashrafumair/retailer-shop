package de.online.retailer.service;

import de.online.retailer.domain.Order;
import de.online.retailer.domain.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    private OrderService orderService;

    @Autowired
    public ShoppingCartService(OrderService orderService) {
        this.orderService = orderService;
    }

    public ShoppingCart addOrder(ShoppingCart shoppingCart, String name, int quantity) {
        logger.debug("adding item to shopping cart with name={}", name);
        Order order = orderService.prepareOrder(name, quantity);
        shoppingCart.getOrders().add(order);
        return shoppingCart;
    }

    public ShoppingCart removeOrder(ShoppingCart shoppingCart, Order order) {
        if (shoppingCart.getOrders().contains(order)) {
            shoppingCart.getOrders().remove(order);
        }
        return shoppingCart;
    }

}

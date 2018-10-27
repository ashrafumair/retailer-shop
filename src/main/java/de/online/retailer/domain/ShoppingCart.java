package de.online.retailer.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShoppingCart {

    public ShoppingCart(List<Order> orders) {
        this.orders = orders;
    }

    private List<Order> orders;
}

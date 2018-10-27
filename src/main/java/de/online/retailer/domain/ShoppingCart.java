package de.online.retailer.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShoppingCart {

    private List<Order> orders;
    private double totalAmount;

}

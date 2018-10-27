package de.online.retailer.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private Item item;
    private Double amount;
    private Double discountAmount; // amount due after applying promotion
    private int quantity;
    private int discountQuantity; // quantity bought after applying promotion
}

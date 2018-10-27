package de.online.retailer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Item item;
    private Double amount;
    private Double discountAmount;
    private int quantity;
    private int discountQuantity;
}

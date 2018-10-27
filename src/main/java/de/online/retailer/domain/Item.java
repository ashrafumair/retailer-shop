package de.online.retailer.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Item {

    @Id
    private Long id;
    private String name;
    private ItemType type;
    private double price;

}

package de.online.retailer;

import de.online.retailer.domain.Item;
import de.online.retailer.domain.ItemType;

public class TestData {

    public static Item getAudioTypeItem(String name, double price) {
        return Item.builder()
                .name(name)
                .type(ItemType.AUDIO)
                .price(price)
                .build();
    }

    public static Item getPowerTypeItem(String name, double price) {
        return Item.builder()
                .name(name)
                .type(ItemType.POWER)
                .price(price)
                .build();
    }


    public static Item getFoodTypeItem(String name, double price) {
        return Item.builder()
                .name(name)
                .type(ItemType.FOOD)
                .price(price)
                .build();
    }

}

package de.online.retailer.service;

import de.online.retailer.TestData;
import de.online.retailer.domain.ShoppingCart;
import de.online.retailer.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceTest {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Mock
    private ItemRepository itemRepository;

    private OrderService orderService;

    private ShoppingCartService shoppingCartService;

    @Before
    public void setup() {
        this.orderService = new OrderService(itemRepository);
        this.shoppingCartService = new ShoppingCartService(orderService);
    }

    @Test
    public void outputShoppingCartContentTest() {
        String item1 = "Speakers";
        String item2 = "AAA Batteries";
        String item3 = "Protein Bars (Box)";
        when(itemRepository.findByName(item1)).thenReturn(Optional.ofNullable(TestData.getAudioTypeItem(item1, 85.0d)));
        when(itemRepository.findByName(item2)).thenReturn(Optional.ofNullable(TestData.getPowerTypeItem(item2, 0.85)));
        when(itemRepository.findByName(item3)).thenReturn(Optional.ofNullable(TestData.getFoodTypeItem(item3, 25.0d)));
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());

        shoppingCartService.addOrder(shoppingCart, item1, 1);
        shoppingCartService.addOrder(shoppingCart, item2, 5);
        shoppingCartService.addOrder(shoppingCart, item3, 2);

        shoppingCart.getOrders().forEach(order -> logger.debug("order: "+ order));

    }

}
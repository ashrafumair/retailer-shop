package de.online.retailer.service;

import de.online.retailer.TestData;
import de.online.retailer.domain.Item;
import de.online.retailer.domain.ItemType;
import de.online.retailer.domain.Order;
import de.online.retailer.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private ItemRepository itemRepository;

    private OrderService orderService;

    @Before
    public void setup() {
        this.orderService = new OrderService(itemRepository);
    }

    @Test
    public void promotionOn1AudioItemTest() {
        final String itemName = "Headphones";
        Item headphones = TestData.getAudioTypeItem(itemName, 150.0d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(headphones));

        Order order = orderService.prepareOrder(itemName, 1);
        assertThat(order.getItem().getName()).isEqualTo(itemName);
        assertThat(order.getItem().getType()).isEqualTo(ItemType.AUDIO);
        assertThat(order.getAmount()).isEqualTo(150.0d);
        assertThat(order.getDiscountAmount()).isEqualTo(105.0d);
        assertThat(order.getQuantity()).isEqualTo(1);
        assertThat(order.getDiscountQuantity()).isEqualTo(1);

        verify(itemRepository, times(1)).findByName(itemName);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void promotionOn3AudioItemsTest() {
        final String itemName = "Headphones";
        Item headphones = TestData.getAudioTypeItem(itemName, 150.0d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(headphones));

        Order order = orderService.prepareOrder(itemName, 3);
        assertThat(order.getItem().getName()).isEqualTo(itemName);
        assertThat(order.getItem().getType()).isEqualTo(ItemType.AUDIO);
        assertThat(order.getAmount()).isEqualTo(450.0d);
        assertThat(order.getDiscountAmount()).isEqualTo(315.0d);
        assertThat(order.getQuantity()).isEqualTo(3);
        assertThat(order.getDiscountQuantity()).isEqualTo(3);

        verify(itemRepository, times(1)).findByName(itemName);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void promotionOn2AAABatteriesTest() {
        final String itemName = "AAA Batteries";
        Item batteries = TestData.getPowerTypeItem(itemName, 0.85d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(batteries));

        Order order = orderService.prepareOrder(itemName, 2);
        assertThat(order.getItem().getName()).isEqualTo(itemName);
        assertThat(order.getItem().getType()).isEqualTo(ItemType.POWER);
        assertThat(order.getAmount()).isEqualTo(1.70d);
        assertThat(order.getDiscountAmount()).isEqualTo(1.70d);
        assertThat(order.getQuantity()).isEqualTo(2);
        assertThat(order.getDiscountQuantity()).isEqualTo(3);

        verify(itemRepository, times(1)).findByName(itemName);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void promotionOn5AAABatteriesTest() {
        final String itemName = "AAA Batteries";
        Item batteries = TestData.getPowerTypeItem(itemName, 0.85d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(batteries));

        Order order = orderService.prepareOrder(itemName, 5);
        assertThat(order.getItem().getName()).isEqualTo(itemName);
        assertThat(order.getItem().getType()).isEqualTo(ItemType.POWER);
        assertThat(order.getAmount()).isEqualTo(4.25d);
        assertThat(order.getDiscountAmount()).isEqualTo(4.25d);
        assertThat(order.getQuantity()).isEqualTo(5);
        assertThat(order.getDiscountQuantity()).isEqualTo(7);

        verify(itemRepository, times(1)).findByName(itemName);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void noPromotionOn1AAABatteriesTest() {
        final String itemName = "AAA Batteries";
        Item batteries = TestData.getPowerTypeItem(itemName, 0.85d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(batteries));

        Order order = orderService.prepareOrder(itemName, 1);
        assertThat(order.getItem().getName()).isEqualTo(itemName);
        assertThat(order.getItem().getType()).isEqualTo(ItemType.POWER);
        assertThat(order.getAmount()).isEqualTo(0.85d);
        assertThat(order.getDiscountAmount()).isEqualTo(0.85d);
        assertThat(order.getQuantity()).isEqualTo(1);
        assertThat(order.getDiscountQuantity()).isEqualTo(1);

        verify(itemRepository, times(1)).findByName(itemName);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void noPromotionOn2ChargersTest() {
        final String itemName = "Chargers";
        Item batteries = TestData.getPowerTypeItem(itemName, 5d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(batteries));

        Order order = orderService.prepareOrder(itemName, 2);
        assertThat(order.getItem().getName()).isEqualTo(itemName);
        assertThat(order.getItem().getType()).isEqualTo(ItemType.POWER);
        assertThat(order.getAmount()).isEqualTo(10.0d);
        assertThat(order.getDiscountAmount()).isEqualTo(10.0d);
        assertThat(order.getQuantity()).isEqualTo(2);
        assertThat(order.getDiscountQuantity()).isEqualTo(2);

        verify(itemRepository, times(1)).findByName(itemName);
        verifyNoMoreInteractions(itemRepository);
    }


/*
    @Test
    public void testNoPromotionOnFoodItems() {
        final String itemName = "Protein Bars";
        Item proteinBars = TestData.getFoodTypeItem(itemName, 25.0d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(proteinBars));

        Item itemAfterPromotion = orderService.selectItem(itemName);
        assertThat(itemAfterPromotion.getName()).isEqualTo(itemName);
        assertThat(itemAfterPromotion.getType()).isEqualTo(ItemType.FOOD);
        assertThat(itemAfterPromotion.getPrice()).isEqualTo(25.0d);
    }

    @Test
    public void testNoPromotionOnAABatteries() {
        final String itemName = "Protein Bars";
        Item proteinBars = TestData.getFoodTypeItem(itemName, 25.0d);
        when(itemRepository.findByName(itemName)).thenReturn(Optional.ofNullable(proteinBars));

        Item itemAfterPromotion = orderService.selectItem(itemName);
        assertThat(itemAfterPromotion.getName()).isEqualTo(itemName);
        assertThat(itemAfterPromotion.getType()).isEqualTo(ItemType.FOOD);
        assertThat(itemAfterPromotion.getPrice()).isEqualTo(25.0d);
    }
    */
}
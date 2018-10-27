package de.online.retailer.service;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import de.online.retailer.domain.Item;
import de.online.retailer.domain.Order;
import de.online.retailer.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    public OrderService() {
    }

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    private ItemRepository itemRepository;

    public OrderService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Order prepareOrder(final String name, final int quantity) {
        Optional<Item> itemOptional = this.itemRepository.findByName(name);

        if (!itemOptional.isPresent()) {
            return null;
        }
        Item item = itemOptional.get();
        Order order = Order.builder()
                .item(item)
                .amount(item.getPrice() * quantity)
                .quantity(quantity)
                .build();

        applyPricePromotions(order);
        applyQuantityPromotions(order);

        return order;
    }

    private void applyPricePromotions(Order order) {
        RuleBook<Double> ruleBook = RuleBookBuilder.create(PricePromotionRules.class)
                .withResultType(Double.class)
                .withDefaultResult(order.getAmount())
                .build();
        NameValueReferableMap facts = new FactMap<Order>();
        facts.setValue("order", order);
        ruleBook.run(facts);
        Double discountedAmount = ruleBook.getResult().map(a -> a.getValue()).orElse(order.getAmount());
        order.setDiscountAmount(discountedAmount);
    }

    private void applyQuantityPromotions(Order order) {
        RuleBook<Integer> ruleBook = RuleBookBuilder.create(QuantityPromotionRules.class)
                .withResultType(Integer.class)
                .withDefaultResult(order.getQuantity())
                .build();
        NameValueReferableMap facts = new FactMap<Order>();
        facts.setValue("order", order);
        ruleBook.run(facts);
        Integer quantityAfterPromotion = ruleBook.getResult().map(a -> a.getValue()).orElse(order.getQuantity());
        order.setDiscountQuantity(quantityAfterPromotion);

    }
}

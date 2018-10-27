package de.online.retailer.service;

import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import de.online.retailer.domain.Item;
import de.online.retailer.domain.Order;

public class QuantityPromotionRules extends CoRRuleBook<Integer> {
    private static final String AAA_BATTERIES = "AAA Batteries";
    public static final Integer OFFER_QUANTITY = 2;

    public QuantityPromotionRules() {
    }

    @Override
    public void defineRules() {

        // 3 for the price of 2 for AAA Batteries
        addRule(RuleBuilder.create()
                .withFactType(Order.class)
                .withResultType(Integer.class)
                .when(facts -> quantityDiscountCondition(facts.getOne().getItem(), facts.getOne().getQuantity()))
                .then((facts, result) -> result.setValue(result.getValue() + (result.getValue() / OFFER_QUANTITY)))
                .stop()
                .build());
    }

    private boolean quantityDiscountCondition(Item item, int quantity) {
        return item.getName().equalsIgnoreCase(AAA_BATTERIES) && quantity >= OFFER_QUANTITY;
    }


}

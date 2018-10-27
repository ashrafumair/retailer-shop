package de.online.retailer.service;

import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import de.online.retailer.domain.ItemType;
import de.online.retailer.domain.Order;

public class PricePromotionRules extends CoRRuleBook<Double> {
    public static final Double PERCENT_PROMOTION = 0.7d;

    @Override
    public void defineRules() {
        // 30% discount on type=audio
        addRule(RuleBuilder.create()
                .withFactType(Order.class)
                .withResultType(Double.class)
                .when(facts -> facts.getOne().getItem().getType().equals(ItemType.AUDIO))
                .then((facts, result) -> result.setValue(result.getValue() * PERCENT_PROMOTION))
                .build());
    }

}

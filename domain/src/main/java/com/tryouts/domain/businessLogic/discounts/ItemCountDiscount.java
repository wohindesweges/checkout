package com.tryouts.domain.businessLogic.discounts;

import com.tryouts.entity.PricingRule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ItemCountDiscount implements ItemDiscount {
	int discountType= 1;
    public double calculateItemDiscount(double sum, PricingRule pricingRule, int itemCount) {
        double matchedThresholds = Math.floor((double) itemCount / pricingRule.getThreshold());
        double openItems = itemCount % pricingRule.getThreshold();
        sum = sum + (matchedThresholds * pricingRule.getSpecialPrice());
        sum = sum + openItems * pricingRule.getPrice();
        return sum;
    }

	@Override
	public int getType() {
		return discountType;
	}
}

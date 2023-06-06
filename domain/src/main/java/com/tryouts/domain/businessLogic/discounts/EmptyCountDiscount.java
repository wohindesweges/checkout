package com.tryouts.domain.businessLogic.discounts;

import com.tryouts.entity.PricingRule;
import org.springframework.stereotype.Component;

@Component
public class EmptyCountDiscount implements ItemDiscount {
	int discountType= 0;
    public double calculateItemDiscount(double sum, PricingRule pricingRule, int itemCount) {
        return sum;
    }

	@Override
	public int getType() {
		return discountType;
	}
}

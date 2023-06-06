package com.tryouts.domain.businessLogic.discounts;

import com.tryouts.entity.PricingRule;

public interface ItemDiscount {
	public double calculateItemDiscount(double sum, PricingRule pricingRule, int itemCount);

	int getType();
}


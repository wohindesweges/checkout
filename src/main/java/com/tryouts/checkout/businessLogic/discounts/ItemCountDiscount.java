package com.tryouts.checkout.businessLogic.discounts;

import com.tryouts.checkout.entity.PricingRule;

public class ItemCountDiscount {
    public double getSumForItemCountDiscount(double sum, PricingRule pricingRule, int itemCount) {
        double matchedThresholds = Math.floor((double) itemCount / pricingRule.getThreshold());
        double openItems = itemCount % pricingRule.getThreshold();
        sum = sum + (matchedThresholds * pricingRule.getSpecialPrice());
        sum = sum + openItems * pricingRule.getPrice();
        return sum;
    }
}

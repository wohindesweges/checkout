package com.tryouts.dto;

import com.tryouts.entity.PricingRule;
import com.tryouts.entity.StockItem;

public class PricingRuleDto extends Dto<PricingRule> {

    Integer threshold;
    Double specialPrice;
    Double price;
    Integer discountType;
    private StockItem stockItem;

    public PricingRuleDto() {
    }

    @Override
    public String getAllRelationDiscription() {
        return "PricingRules";
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public PricingRuleDto setThreshold(int threshold) {
        this.threshold = threshold;
        return this;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public PricingRuleDto setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public PricingRuleDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public PricingRuleDto setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
        return this;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public PricingRuleDto setDiscountType(int discountType) {
        this.discountType = discountType;
        return this;
    }

    @Override
    public PricingRule getEntityModel() {
        return new PricingRule()//
                .setId(this.id)
                .setPrice(this.price)//
                .setStockItemId(this.stockItem)//
                .setThreshold(this.threshold)//
                .setSpecialPrice(this.specialPrice);
    }

    public PricingRuleDto setID(Long id) {
        this.id = id;
        return this;
    }
}

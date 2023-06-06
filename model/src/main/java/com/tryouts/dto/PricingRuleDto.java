package com.tryouts.dto;

import com.tryouts.entity.PricingRule;
import com.tryouts.entity.StockItem;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PricingRuleDto extends Dto<PricingRule> {

	int threshold;
	Double specialPrice;
	double price;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stockItemId")
	private StockItem stockItem;
	int discountType;
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

	public int getThreshold() {
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

	public double getPrice() {
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

	public int getDiscountType() {
		return discountType;
	}

	public PricingRuleDto setDiscountType(int discountType) {
		this.discountType = discountType;
		return this;
	}

	@Override
	public PricingRule getEntityModel() {
		return new PricingRule()//
				.setPrice(this.price)//
				.setStockItemId(this.stockItem)//
				.setThreshold(this.threshold)//
				.setSpecialPrice(this.specialPrice);
	}

	public PricingRuleDto setID(Long id) {
		this.id=id;
		return this;
	}
}

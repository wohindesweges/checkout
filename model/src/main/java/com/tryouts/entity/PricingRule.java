package com.tryouts.entity;

import com.tryouts.dto.PricingRuleDto;
import com.tryouts.entity.exception.NotValid;
import jakarta.persistence.*;

@Entity
public class PricingRule extends ModelEntity<PricingRuleDto> {
    @Id
    @GeneratedValue
    Long id;
    int threshold;
    Double specialPrice;
    double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stockItemId")
    private StockItem stockItem;

    @Override
    public Long getId() {
        return id;
    }

    public PricingRule setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public void validate() throws NotValid {
        //todo price > 0
    }

    @Override
    public PricingRuleDto getDto() {
        return new PricingRuleDto();
    }//TODO

    public int getThreshold() {
        return threshold;
    }

    public PricingRule setThreshold(int threshhold) {
        this.threshold = threshhold;
        return this;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public PricingRule setSpecialPrice(double specialPriceForThreshold) {
        this.specialPrice = specialPriceForThreshold;
        return this;
    }

    public PricingRule setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
        return this;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public PricingRule setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public PricingRule setPrice(double price) {
        this.price = price;
        return this;
    }
}

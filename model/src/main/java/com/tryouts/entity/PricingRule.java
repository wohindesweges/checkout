package com.tryouts.entity;

import com.tryouts.dto.PricingRuleDto;
import com.tryouts.entity.exception.NotValid;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PricingRule extends ModelEntity<PricingRuleDto> {
    @Id
    @GeneratedValue
    Long id;
    Integer threshold;
    Double specialPrice;
    Double price;
    Integer discountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stockItemId", referencedColumnName = "id")
    private StockItem stockItemId;

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
        if (price <= 0) {
            throw new NotValid("Price must be greater than 0", this);
        }
        if (stockItemId == null) {
            throw new NotValid("Related Stockitem must be set", this);
        }
        if (stockItemId.getId() == null) {
            throw new NotValid("Related Stockitem is unkown", this);
        }
    }

    @Override
    public PricingRuleDto getDto() {
        return new PricingRuleDto()//
                .setPrice(this.price)//
                .setThreshold(this.threshold)
                .setStockItem(this.stockItemId)
                .setSpecialPrice(this.specialPrice)
                .setID(this.id);
    }

    @Override
    public void updateByDto(PricingRuleDto dto) {
        if (dto.getPrice() > 0) {
            this.price = dto.getPrice();
        }
        this.specialPrice = dto.getSpecialPrice();
        if (dto.getStockItem() != null) {
            this.stockItemId = dto.getStockItem();
        }
        this.threshold = dto.getThreshold();

    }

    public Integer getThreshold() {
        return threshold;
    }

    public PricingRule setThreshold(int threshhold) {
        this.threshold = threshhold;
        return this;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public PricingRule setSpecialPrice(Double specialPriceForThreshold) {
        this.specialPrice = specialPriceForThreshold;
        return this;
    }

    public StockItem getStockItemId() {
        return stockItemId;
    }

    public PricingRule setStockItemId(StockItem stockItem) {
        this.stockItemId = stockItem;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public PricingRule setPrice(double price) {
        this.price = price;
        return this;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public PricingRule setDiscountType(int discountType) {
        this.discountType = discountType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricingRule that = (PricingRule) o;
        return threshold == that.threshold && Double.compare(that.price, price) == 0 && Objects.equals(specialPrice, that.specialPrice) && Objects.equals(
                stockItemId, that.stockItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, threshold, specialPrice, price, stockItemId);
    }
}

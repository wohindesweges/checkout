package com.tryouts.checkout.repository;

import com.tryouts.checkout.entity.PricingRule;
import com.tryouts.checkout.entity.StockItem;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {

    default Optional<PricingRule> findForStockItem(StockItem stockItem) {
        return findOne(Example.of(new PricingRule().setStockItem(stockItem)));
    }
}

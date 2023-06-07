package com.tryouts.repository.jpa;

import com.tryouts.dto.PricingRuleDto;
import com.tryouts.entity.PricingRule;
import com.tryouts.entity.StockItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {

    default Optional<PricingRule> findForStockItem(StockItem stockItem) {
        PricingRule probe = new PricingRule().setStockItemId(stockItem);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()//same as matchingAll()
                .withIgnorePaths("id");
        Optional<PricingRule> one = findOne(Example.of(probe, exampleMatcher));
        return one;
    }

    default PricingRuleDto savePricing(PricingRuleDto rule) {
        final PricingRule modelEntity = rule.getEntityModel();
        return save(modelEntity).getDto();
    }
}

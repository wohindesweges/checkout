package com.tryouts.repository.jpa;

import com.tryouts.dto.PricingRuleDto;
import com.tryouts.entity.PricingRule;
import com.tryouts.entity.StockItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {

	default Optional<PricingRule> findForStockItem(StockItem stockItem) {
		return findOne(Example.of(new PricingRule().setStockItemId(stockItem), ExampleMatcher.matchingAny()));
	}

	default PricingRuleDto savePricing(PricingRuleDto rule) {
		final PricingRule modelEntity = rule.getEntityModel();
		return saveAndFlush(modelEntity).getDto();
	}
}

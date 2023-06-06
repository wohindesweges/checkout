package com.tryouts.domain.businessLogic;

import com.tryouts.dto.PricingRuleDto;
import com.tryouts.entity.PricingRule;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.jpa.PricingRuleRepository;
import com.tryouts.repository.jpa.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PricingActions {
    private final PricingRuleRepository pricingRuleRepository;
	private final StockItemRepository stockItemRepository;

	@Autowired
    public PricingActions(PricingRuleRepository pricingRuleRepository, StockItemRepository stockItemRepository) {
		this.stockItemRepository = stockItemRepository;

		this.pricingRuleRepository = pricingRuleRepository;
    }

    public PricingRuleDto setNewPricingRule(PricingRuleDto rule) {
		final StockItem stockItemRepositoryByName = stockItemRepository.findByName(rule.getStockItem().getName());
		final Optional<PricingRule> pricingForStockItem = pricingRuleRepository.findForStockItem(stockItemRepositoryByName);
		PricingRule modelEntity;
		if (pricingForStockItem.isPresent()) {
			rule.setStockItem(stockItemRepositoryByName);
			pricingForStockItem.get().updateByDto(rule);
		}else{
			modelEntity= rule.getEntityModel();
			modelEntity.setStockItemId(stockItemRepositoryByName);
			modelEntity.validate();
			rule=modelEntity.getDto();
		}
        return pricingRuleRepository.savePricing(rule);
    }


}

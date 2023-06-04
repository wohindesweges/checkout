package com.tryouts.domain.businessLogic;

import com.tryouts.dto.PricingRuleDto;
import com.tryouts.entity.PricingRule;
import com.tryouts.repository.jpa.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingActions {
    private final PricingRuleRepository pricingRuleRepository;

    @Autowired
    public PricingActions(PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public PricingRuleDto setNewPricingRule(PricingRuleDto rule) {
        PricingRule modelEntity = rule.getModelEntity();
        modelEntity.validate();
        return pricingRuleRepository.savePricing(rule).getDto();
    }


}

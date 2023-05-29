package com.tryouts.checkout.businessLogic;

import com.tryouts.checkout.dto.PricingRuleDto;
import com.tryouts.checkout.entity.PricingRule;
import com.tryouts.checkout.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingActions {
    private final PricingRuleRepository pricingRuleRepository;

    @Autowired
    public PricingActions(PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public void setNewPricingRule(PricingRuleDto rule) {
        PricingRule modelEntity = rule.getModelEntity();
        modelEntity.validate();
        pricingRuleRepository.save(modelEntity);
    }


}

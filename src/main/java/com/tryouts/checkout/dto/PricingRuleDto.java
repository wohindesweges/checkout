package com.tryouts.checkout.dto;

import com.tryouts.checkout.controller.Controller;
import com.tryouts.checkout.entity.PricingRule;

public class PricingRuleDto extends Dto<PricingRule> {


    @Override
    public Class<? extends Controller> getController() {
        return null;
    }

    @Override
    public String getAllRelationDiscription() {
        return null;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public PricingRule getModelEntity() {
        return null;
    }
}

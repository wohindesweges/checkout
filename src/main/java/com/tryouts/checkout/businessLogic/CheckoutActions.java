package com.tryouts.checkout.businessLogic;

import com.tryouts.checkout.dto.PricingRuleDto;
import com.tryouts.checkout.entity.PricingRule;
import com.tryouts.checkout.entity.StockItem;
import com.tryouts.checkout.repository.PricingRuleRepository;
import com.tryouts.checkout.repository.StockItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckoutActions {
    private final PricingRuleRepository pricingRuleRepository;
    private final StockItemRepository stockItemRepository;
    Logger LOG = LogManager.getLogger(CheckoutActions.class);


    @Autowired
    public CheckoutActions(PricingRuleRepository pricingRuleRepository, StockItemRepository stockItemRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
        this.stockItemRepository = stockItemRepository;
    }

    public void setNewPricingRules(List<PricingRuleDto> rule) {
        List<PricingRule> collect = rule.stream().map(PricingRuleDto::getModelEntity).toList();
        collect.forEach(PricingRule::validate);//TODO do not stop at first error
        pricingRuleRepository.saveAll(collect);
    }

    public void setNewPricingRule(PricingRuleDto rule) {
        PricingRule modelEntity = rule.getModelEntity();
        modelEntity.validate();
        pricingRuleRepository.save(modelEntity);
    }

    public void finishCheckout() {

    }

    public double getPriceForItems(String... itemNames) {
        double sum = 0.0d;
        Map<String, Long> itemCountByName = Arrays.stream(itemNames)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        for (String name : itemCountByName.keySet()) {
            final Optional<StockItem> stockItemById = this.stockItemRepository.findByName(name);
            if (stockItemById.isPresent()) {
                StockItem stockItem = stockItemById.get();
                Optional<PricingRule> specialPricing = pricingRuleRepository.findForStockItem(stockItem);
                if (specialPricing.isPresent()) {
                    PricingRule pricingRule = specialPricing.get();
                    Long itemCount = itemCountByName.get(name);
                    if (pricingRule.getSpecialPrice() != null) {
                        double matchedThresholds = Math.floor((double) itemCount / pricingRule.getThreshold());
                        double openItems = itemCount % pricingRule.getThreshold();
                        sum = sum + (matchedThresholds * pricingRule.getSpecialPrice());
                        sum = sum + openItems * pricingRule.getPrice();
                    } else {
                        sum = sum + itemCount * pricingRule.getPrice();
                    }
                } else {
                    LOG.error("Found no price for: " + name);
                }
            } else {
                LOG.error("Found no item found for: " + name);
            }
        }
        return Math.round(sum * 100) / 100.0;
    }

}

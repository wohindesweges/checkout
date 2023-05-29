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
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CheckoutActions {
    private final PricingRuleRepository pricingRuleRepository;
    private final StockItemRepository stockItemRepository;
    private final ArrayList<String> items = new ArrayList<>();
    Logger LOG = LogManager.getLogger(CheckoutActions.class);

    @Autowired
    public CheckoutActions(PricingRuleRepository pricingRuleRepository, StockItemRepository stockItemRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
        this.stockItemRepository = stockItemRepository;
    }

    private static double getSumForItemCountDiscount(double sum, PricingRule pricingRule, int itemCount) {
        double matchedThresholds = Math.floor((double) itemCount / pricingRule.getThreshold());
        double openItems = itemCount % pricingRule.getThreshold();
        sum = sum + (matchedThresholds * pricingRule.getSpecialPrice());
        sum = sum + openItems * pricingRule.getPrice();
        return sum;
    }

    public void scanItem(String item) {
        items.add(item);
    }

    public void setNewPricingRules(List<PricingRuleDto> rule) {
        List<PricingRule> collect = rule.stream().map(PricingRuleDto::getModelEntity).toList();
        collect.forEach(PricingRule::validate);//TODO do not stop at first error
        pricingRuleRepository.saveAll(collect);
    }

    public double getCurrentTotal() {
        return getPriceForItems(String.join("", items));
    }

    public void finishCheckout() {
        items.clear();
    }

    public double getPriceForItems(String itemNames) {
        String[] singleItems = itemNames.split("");
        double sum = 0.0d;
        Map<String, String> itemCountByName = Arrays.stream(singleItems).filter(StringUtils::hasText)
                .collect(Collectors.groupingBy(s -> s, Collectors.joining()));
        for (String name : itemCountByName.keySet()) {
            final Optional<StockItem> stockItemById = this.stockItemRepository.findByName(name);
            if (stockItemById.isPresent()) {
                sum = sum + getPriceForItem(itemCountByName, stockItemById.get());
            } else {
                LOG.error("Found no item found for: " + name);
            }
        }
        return Math.round(sum * 100) / 100.0;
    }

    private double getPriceForItem(Map<String, String> itemCountByName, StockItem stockItem) {
        double sum = 0.0d;
        Optional<PricingRule> pricingRuleOptional = pricingRuleRepository.findForStockItem(stockItem);
        if (pricingRuleOptional.isPresent()) {
            PricingRule pricingRule = pricingRuleOptional.get();
            int itemCount = itemCountByName.get(stockItem.getName()).length();
            if (pricingRule.getSpecialPrice() != null) {
                sum = getSumForItemCountDiscount(sum, pricingRule, itemCount);
            } else {
                sum = sum + itemCount * pricingRule.getPrice();
            }
        } else {
            LOG.error("Found no price for: " + stockItem.getName());
        }
        return sum;
    }

}

package com.tryouts.domain.businessLogic;

import com.tryouts.domain.businessLogic.discounts.EmptyCountDiscount;
import com.tryouts.domain.businessLogic.discounts.ItemCountDiscount;
import com.tryouts.domain.businessLogic.discounts.ItemDiscount;
import com.tryouts.entity.PricingRule;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.jpa.PricingRuleRepository;
import com.tryouts.repository.jpa.StockItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckoutActions {
    private final PricingRuleRepository pricingRuleRepository;
    private final StockItemRepository stockItemRepository;
    private final ArrayList<StockItem> items = new ArrayList<>();
	private final List<ItemDiscount> itemDiscounts;
	Logger LOG = LogManager.getLogger(CheckoutActions.class);

    @Autowired
    public CheckoutActions(PricingRuleRepository pricingRuleRepository, StockItemRepository stockItemRepository, List<ItemDiscount> itemDiscounts) {
		this.itemDiscounts = itemDiscounts;
		this.pricingRuleRepository = pricingRuleRepository;
        this.stockItemRepository = stockItemRepository;
    }


    public void scanItem(String item) {
		final StockItem stockItemByName = stockItemRepository.findByName(item);
		if(stockItemByName!=null){
			items.add(stockItemByName);
		}
    }

    public double getCurrentTotal() {
        return getPriceForItems( items);
    }

	public ArrayList<StockItem> getScannedItems() {
		return items;
	}

	public void finishCheckout() {
        items.clear();
    }
	public double getPriceForItems(String itemNames) {
		Arrays.stream(itemNames.split("")).forEach(this::scanItem);
		return getPriceForItems(items);
	}

    public double getPriceForItems(List<StockItem> itemNames) {
        double sum = 0.0d;
        Map<String, List<StockItem>> itemGroups = itemNames.stream().collect(Collectors.groupingBy(StockItem::getName,Collectors.toList()));
        for (String name : itemGroups.keySet()) {
			if(itemGroups.get(name).size()>0){
				sum = sum + getPriceForItemGroup(itemGroups.get(name));
			}
        }
        return Math.round(sum * 100) / 100.0;
    }

    private double getPriceForItemGroup(List<StockItem> stockItems) {
        double sum = 0.0d;
		final StockItem stockItem = stockItems.get(0);
		Optional<PricingRule> pricingRuleOptional =  this.pricingRuleRepository.findForStockItem(stockItem);
        if (pricingRuleOptional.isPresent()) {
            PricingRule pricingRule = pricingRuleOptional.get();
            int itemCount = stockItems.size();
            if (pricingRule.getSpecialPrice() != null) {
//				stockItem.getDiscountType();
                sum =getDiscountByType(1).calculateItemDiscount(sum, pricingRule, itemCount);
            } else {
                sum = sum + itemCount * pricingRule.getPrice();
            }
        } else {
            LOG.error("Found no price for: " + stockItem.getName());
        }
        return sum;
    }

	private ItemDiscount getDiscountByType( int discountType ) {
		for (ItemDiscount itemDiscount : itemDiscounts) {
			if (itemDiscount.getType()==discountType) {
				return itemDiscount;
			}
		}
		return new EmptyCountDiscount();
	}

}

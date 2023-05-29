package com.tryouts.checkout.businessLogic;

import com.tryouts.checkout.entity.PricingRule;
import com.tryouts.checkout.entity.StockItem;
import com.tryouts.checkout.repository.PricingRuleRepository;
import com.tryouts.checkout.repository.StockItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CheckoutActionsTest {

    private final PricingRule singlePricingRule = new PricingRule().setPrice(10.5d);
    private final PricingRule specialPriceFor3 = new PricingRule().setPrice(10.5d).setSpecialPrice(15d).setThreshold(3);
    @Mock
    PricingRuleRepository pricingRuleRepository;
    @Mock
    StockItemRepository stockItemRepository;
    @InjectMocks
    CheckoutActions checkoutActions;
    private StockItem stockItemA = new StockItem().setName("A").setId(1L);


    @Test
    void getPriceForThreeItems() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn(Optional.of(stockItemA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(singlePricingRule));
        String items = "AAA";
        String[] split = items.split("");
        double priceForItems = checkoutActions.getPriceForItems(split);
        Assertions.assertEquals(31.5d, priceForItems);
    }

    @Test
    void getPriceForItemsSpecialPrice() {
        stockItemA = new StockItem().setName("A");
        Mockito.when(stockItemRepository.findByName("A")).thenReturn(Optional.of(stockItemA.setId(1L)));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(specialPriceFor3));
        String items = "AAA";
        String[] split = items.split("");
        double priceForItems = checkoutActions.getPriceForItems(split);
        Assertions.assertEquals(15d, priceForItems);
    }
}
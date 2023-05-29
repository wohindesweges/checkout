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
    private final PricingRule pricingRuleA = new PricingRule().setPrice(50d).setThreshold(3).setSpecialPrice(130d);
    private final PricingRule pricingRuleB = new PricingRule().setPrice(30d).setThreshold(2).setSpecialPrice(45d);
    private final PricingRule pricingRuleC = new PricingRule().setPrice(20d);
    private final PricingRule pricingRuleD = new PricingRule().setPrice(15d);
    private final StockItem stockItemA = new StockItem().setName("A").setId(1L);
    private final StockItem stockItemB = new StockItem().setName("B").setId(2L);
    private final StockItem stockItemC = new StockItem().setName("C").setId(3L);
    private final StockItem stockItemD = new StockItem().setName("D").setId(4L);
    @InjectMocks

    CheckoutActions checkoutActions;
    @Mock

    PricingRuleRepository pricingRuleRepository;
    @Mock

    StockItemRepository stockItemRepository;


    @Test
    void getPriceForTwoItems() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn(Optional.of(stockItemA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        double priceForItems = checkoutActions.getPriceForItems("AA");
        Assertions.assertEquals(100d, priceForItems);
        checkoutActions.finishCheckout();

    }

    @Test
    void getPriceForItemsSpecialPrice() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn(Optional.of(stockItemA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        double priceForItems = checkoutActions.getPriceForItems("AAA");
        Assertions.assertEquals(130d, priceForItems);
        checkoutActions.finishCheckout();
    }

    @Test
    void getPriceForItemsSpecialPriceTwice() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn(Optional.of(stockItemA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        double priceForItems = checkoutActions.getPriceForItems("AAAAAA");
        Assertions.assertEquals(260d, priceForItems);
        checkoutActions.finishCheckout();
    }


    @Test
    void multipleRules() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn(Optional.of(stockItemA));
        Mockito.when(stockItemRepository.findByName("B")).thenReturn(Optional.of(stockItemB));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemB)).thenReturn(Optional.of(pricingRuleB));
        double priceForItems = checkoutActions.getPriceForItems("AAABBB");
        Assertions.assertEquals(205, priceForItems);
        checkoutActions.finishCheckout();
    }

    @Test
    void scanItemTest() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn(Optional.of(stockItemA));
        Mockito.when(stockItemRepository.findByName("B")).thenReturn(Optional.of(stockItemB));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemB)).thenReturn(Optional.of(pricingRuleB));
        Assertions.assertEquals(0d, checkoutActions.getCurrentTotal());
        checkoutActions.scanItem("A");
        Assertions.assertEquals(50d, checkoutActions.getCurrentTotal());
        checkoutActions.scanItem("B");
        Assertions.assertEquals(80d, checkoutActions.getCurrentTotal());
        checkoutActions.scanItem("A");
        Assertions.assertEquals(130d, checkoutActions.getCurrentTotal());
        checkoutActions.scanItem("A");
        Assertions.assertEquals(160d, checkoutActions.getCurrentTotal());
        checkoutActions.scanItem("B");
        Assertions.assertEquals(175d, checkoutActions.getCurrentTotal());
        checkoutActions.finishCheckout();
    }


}
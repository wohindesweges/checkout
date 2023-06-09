package com.tryouts.domain.businessLogic;

import com.tryouts.domain.businessLogic.discounts.ItemCountDiscount;
import com.tryouts.entity.PricingRule;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.jpa.PricingRuleRepository;
import com.tryouts.repository.jpa.StockItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@SpringBootTest
@ContextConfiguration(classes = {CheckoutActions.class, ItemCountDiscount.class, PricingRuleRepository.class, StockItemRepository.class})
class CheckoutActionsTest {
    private final PricingRule pricingRuleA = new PricingRule().setPrice(50d).setThreshold(3).setSpecialPrice(130d).setDiscountType(1);
    private final PricingRule pricingRuleB = new PricingRule().setPrice(30d).setThreshold(2).setSpecialPrice(45d).setDiscountType(1);
    //    private final PricingRule pricingRuleC = new PricingRule().setPrice(20d);
    //    private final PricingRule pricingRuleD = new PricingRule().setPrice(15d);
    private final StockItem stockItemA = new StockItem().setName("A").setId(1L);
    private final StockItem stockItemB = new StockItem().setName("B").setId(2L);
    private final StockItem stockItemC = new StockItem().setName("C").setId(3L);
    private final StockItem stockItemD = new StockItem().setName("D").setId(4L);
    @Autowired
    CheckoutActions checkoutActions;
    @MockBean
    private PricingRuleRepository pricingRuleRepository;
    @MockBean
    private StockItemRepository stockItemRepository;


    @Test
    void getPriceForTwoItems() {
        Mockito.when(stockItemRepository.findByName(Mockito.anyString())).thenReturn((stockItemA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        double priceForItems = checkoutActions.getPriceForItems("AA");
        Assertions.assertEquals(100d, priceForItems);
        checkoutActions.finishCheckout();

    }

    @Test
    void getPriceForItemsSpecialPrice() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn((stockItemA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        double priceForItems = checkoutActions.getPriceForItems("AAA");
        Assertions.assertEquals(130d, priceForItems);
        checkoutActions.finishCheckout();
    }

    @Test
    void getPriceForItemsSpecialPriceTwice() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn((stockItemA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        double priceForItems = checkoutActions.getPriceForItems("AAAAAA");
        Assertions.assertEquals(260d, priceForItems);
        checkoutActions.finishCheckout();
    }


    @Test
    void multipleRules() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn((stockItemA));
        Mockito.when(stockItemRepository.findByName("B")).thenReturn((stockItemB));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemB)).thenReturn(Optional.of(pricingRuleB));
        double priceForItems = checkoutActions.getPriceForItems("AAABBB");
        Assertions.assertEquals(205, priceForItems);
        checkoutActions.finishCheckout();
    }

    @Test
    void allItems() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn((stockItemA));
        Mockito.when(stockItemRepository.findByName("B")).thenReturn((stockItemB));
        Mockito.when(stockItemRepository.findByName("C")).thenReturn((stockItemC));
        Mockito.when(stockItemRepository.findByName("D")).thenReturn((stockItemD));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemA)).thenReturn(Optional.of(pricingRuleA));
        Mockito.when(pricingRuleRepository.findForStockItem(stockItemB)).thenReturn(Optional.of(pricingRuleB));
        double priceForItems = checkoutActions.getPriceForItems("ABCD");
        Assertions.assertEquals(80, priceForItems);
        checkoutActions.finishCheckout();
    }

    @Test
    void scanItemTest() {
        Mockito.when(stockItemRepository.findByName("A")).thenReturn((stockItemA));
        Mockito.when(stockItemRepository.findByName("B")).thenReturn((stockItemB));
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
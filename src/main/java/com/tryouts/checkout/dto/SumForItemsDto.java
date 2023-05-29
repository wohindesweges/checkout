package com.tryouts.checkout.dto;

import com.tryouts.checkout.controller.Controller;
import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.checkout.entity.StockItem;

import java.util.List;

public class SumForItemsDto extends Dto<StockItem> {

    List<StockItem> items;

    @Override
    public Class<? extends Controller> getController() {
        return StockItemController.class;
    }

    @Override
    public String getAllRelationDiscription() {
        return "forItems";
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public StockItem getModelEntity() {
        return StockItem.empty();
    }
}

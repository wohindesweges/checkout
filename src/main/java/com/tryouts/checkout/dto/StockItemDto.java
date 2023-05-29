package com.tryouts.checkout.dto;

import com.tryouts.checkout.controller.Controller;
import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.checkout.entity.StockItem;

public class StockItemDto extends Dto<StockItem> {

    String name;

    @Override
    public Class<? extends Controller> getController() {
        return StockItemController.class;
    }

    @Override
    public String getAllRelationDiscription() {
        return "StockUnits";
    }

    @Override
    public Long getId() {
        return id;
    }

    public StockItemDto setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public StockItem getModelEntity() {
        return new StockItem().setName(this.name);
    }

    public String getName() {
        return name;
    }

    public StockItemDto setName(String name) {
        this.name = name;
        return this;
    }
}

package com.tryouts.dto;

import com.tryouts.entity.StockItem;

import java.util.List;

public class SumForItemsDto extends Dto<StockItem> {

    List<StockItem> items;


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

package com.tryouts.dto;

import com.tryouts.entity.StockItem;

public class StockItemDto extends Dto<StockItem> {

    String name;
	String description;

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
    public StockItem getEntityModel() {
        return new StockItem().setName(this.name);
    }

    public String getName() {
        return name;
    }

    public StockItemDto setName(String name) {
        this.name = name;
        return this;
    }

	public String getDescription() {
		return description;
	}

	public StockItemDto setDescription(String description) {
		this.description = description;
		return this;
	}
}

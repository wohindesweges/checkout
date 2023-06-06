package com.tryouts.dto;

import com.tryouts.entity.StockItem;

import java.util.List;

public class SumForCheckoutDto {

	private final Double sum;
	List<StockItem> items;

	public SumForCheckoutDto(Double sum, List<StockItem> items) {
		this.items = items;
		this.sum = sum;
	}


	public void setItems(List<StockItem> items) {
		this.items = items;
	}

	public List<StockItem> getItems() {
		return items;
	}

	public Double getSum() {
		return sum;
	}
}

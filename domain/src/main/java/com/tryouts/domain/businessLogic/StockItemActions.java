package com.tryouts.domain.businessLogic;

import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.jpa.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockItemActions {

    private final StockItemRepository repository;

    @Autowired
    public StockItemActions(StockItemRepository repository) {
        this.repository = repository;
    }


	public StockItemDto addStockItem(StockItemDto dto) {
		final StockItem modelEntity = dto.getModelEntity();
		modelEntity.validate();
		final StockItem save = repository.save(modelEntity);
		return save.getDto();
	}
}

package com.tryouts.domain.businessLogic;

import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.exception.EntityAlreadyExsists;
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
		final StockItem modelEntity = dto.getEntityModel();
		modelEntity.validate();
		final StockItem byName = repository.findByName(dto.getName());
		StockItem save;
		if (byName == null) {
			save = repository.save(modelEntity);
		} else {
			throw new EntityAlreadyExsists(byName);
		}
		return save.getDto();
	}

	public StockItemDto saveOrUpdate(StockItemDto dto) {
		final StockItem modelEntity = dto.getEntityModel();
		modelEntity.validate();
		final StockItem byName = repository.findByName(dto.getName());
		StockItem save;
		if (byName == null) {
			save = repository.save(modelEntity);
		} else {
			byName.updateByDto(dto);
			save = repository.save(byName);
		}
		return save.getDto();
	}
}

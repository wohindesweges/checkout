package com.tryouts.checkout.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tryouts.checkout.representation.assembler.StockItemAssembler;
import com.tryouts.domain.businessLogic.StockItemActions;
import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.StockItem;
import com.tryouts.entity.exception.NotValid;
import com.tryouts.repository.exception.EntityAlreadyExsists;
import com.tryouts.repository.jpa.StockItemRepository;
import com.tryouts.repository.exception.EntityNotFound;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@ComponentScan(basePackages={"com.tryouts.*"})
		@RestController
public class StockItemController  {
    private static final String pathRoot = "stockitem";
    private final StockItemActions stockItemActions;
    private final StockItemRepository repository;
	private final StockItemAssembler stockItemAssembler;
	public StockItemController(StockItemActions stockItemActions, StockItemRepository stockItemRepository, StockItemAssembler stockItemAssembler) {
        this.stockItemActions = stockItemActions;
        this.repository = stockItemRepository;
		this.stockItemAssembler = stockItemAssembler;
	}

    @GetMapping("/" + pathRoot + "/{id}")
    public EntityModel<StockItemDto> findByID(@PathVariable Long id) {
		final Optional<StockItem> byId = repository.findById(id);
		StockItem entityModel = byId.orElseThrow(() -> new EntityNotFound("Requested element not found"));
		return stockItemAssembler.toModel(entityModel.getDto());
    }

	@GetMapping("/" + pathRoot + "/")
	public EntityModel<StockItemDto> findByName(@RequestParam(value="name") String name) {
		StockItem byName = repository.findByName(name);
		return stockItemAssembler.toModel(byName.getDto());
	}

    @GetMapping("/" + pathRoot)
    public CollectionModel<EntityModel<StockItemDto>> all() {
		return stockItemAssembler.toCollectionModel(repository.findAll().stream().map(StockItem::getDto).toList());
    }

    @PostMapping("/" + pathRoot)
    public ResponseEntity<?> post(@RequestBody StockItemDto dto) throws NotValid {
		try{
			final StockItemDto stockItemDto = stockItemActions.addStockItem(dto);
			return new ResponseEntity<>(stockItemDto, HttpStatus.OK);
		}catch (EntityAlreadyExsists entityAlreadyExsists){
			return new ResponseEntity<>(entityAlreadyExsists.getExistsingEntity(), HttpStatus.CONFLICT);
		}
    }

	@PutMapping("/" + pathRoot)
	public ResponseEntity<?> put(@RequestBody StockItemDto dto) throws NotValid {
			final StockItemDto stockItemDto = stockItemActions.saveOrUpdate(dto);
			return new ResponseEntity<>(stockItemDto, HttpStatus.OK);
	}

    @DeleteMapping("/" + pathRoot + "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.I_AM_A_TEAPOT)).build();
    }



}

package com.tryouts.checkout.controller;

import com.tryouts.checkout.businessLogic.StockItemActions;
import com.tryouts.checkout.dto.StockItemDto;
import com.tryouts.checkout.entity.StockItem;
import com.tryouts.checkout.entity.exception.NotValid;
import com.tryouts.checkout.repository.StockItemRepository;
import com.tryouts.checkout.representation.assembler.StockItemAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockItemController extends Controller<StockItem, StockItemDto, StockItemRepository, StockItemAssembler> {
    private static final String pathRoot = "stockitem";
    private final StockItemActions stockItemActions;

    public StockItemController(StockItemActions stockItemActions) {
        this.stockItemActions = stockItemActions;
    }

    @GetMapping("/" + pathRoot + "/{id}")
    @Override
    public EntityModel<StockItemDto> findByID(@PathVariable Long id) {
        return super.findByID(id);
    }

    @GetMapping("/" + pathRoot)
    @Override
    public CollectionModel<EntityModel<StockItemDto>> all() {
        return super.all();
    }

    @PostMapping("/" + pathRoot)
    @Override
    public EntityModel<StockItemDto> put(@RequestBody StockItemDto dto) throws NotValid {
        return super.put(dto);
    }

    @DeleteMapping("/" + pathRoot + "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @GetMapping("/" + pathRoot + "/{name}")
    public EntityModel<StockItemDto> findByName(@PathVariable String name) {
        StockItem byName = stockItemActions.findByName(name);
        return assembler.toModel(byName.getDto());
    }
}

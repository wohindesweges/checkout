package com.tryouts.checkout.businessLogic;

import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.checkout.controller.exception.EntityNotFound;
import com.tryouts.checkout.entity.StockItem;
import com.tryouts.checkout.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockItemActions {

    private final StockItemRepository repository;

    @Autowired
    public StockItemActions(StockItemRepository repository) {
        this.repository = repository;
    }

    public StockItem findByName(String name) {
        final Optional<StockItem> byId = this.repository.findOne(Example.of(new StockItem().setName(name), ExampleMatcher.matchingAny()));
        return byId.orElseThrow(() -> new EntityNotFound("Requested element not found", new StockItemController(this)));
    }


}

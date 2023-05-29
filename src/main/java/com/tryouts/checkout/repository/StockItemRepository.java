package com.tryouts.checkout.repository;

import com.tryouts.checkout.entity.StockItem;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    default Optional<StockItem> findByName(String name) {
        return this.findOne(Example.of(new StockItem().setName(name)));
    }
}

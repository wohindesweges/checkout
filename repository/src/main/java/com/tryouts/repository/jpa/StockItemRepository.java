package com.tryouts.repository.jpa;

import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.exception.EntityNotFound;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

	StockItem findByName(String name);



}


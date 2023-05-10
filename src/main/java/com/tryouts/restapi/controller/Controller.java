package com.tryouts.restapi.controller;

import com.tryouts.restapi.model.ModelEntity;
import com.tryouts.restapi.processor.assembler.Assembler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ServerErrorException;

public abstract class Controller<E extends ModelEntity, R extends JpaRepository<E, Long>, A extends Assembler<E>> {

    R repository;

    A assembler;


    public EntityModel<E> findByID(@PathVariable Long id) {
        E entityModel = this.repository.findById(id).orElseThrow(() -> new ServerErrorException("ONLY:TEST", null));
        return assembler.toModel(entityModel);
    }


    public CollectionModel<EntityModel<E>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }
}

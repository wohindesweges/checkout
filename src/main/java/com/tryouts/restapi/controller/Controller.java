package com.tryouts.restapi.controller;

import com.tryouts.restapi.controller.exception.EntityNotFound;
import com.tryouts.restapi.entity.ModelEntity;
import com.tryouts.restapi.entity.exception.NotValid;
import com.tryouts.restapi.processor.assembler.Assembler;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class Controller<E extends ModelEntity, R extends JpaRepository<E, Long>, A extends Assembler<E>> {


    R repository;
    A assembler;


    public EntityModel<E> findByID(@PathVariable Long id) {
        E entityModel = this.repository.findById(id).orElseThrow(() -> new EntityNotFound("Requested element not found", this));
        return assembler.toModel(entityModel);
    }


    public CollectionModel<EntityModel<E>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }


    public EntityModel<E> put(@RequestBody E newModelEntity) throws NotValid {
        E savedModelEntity = null;
        newModelEntity.validate();
        if (repository.exists(Example.of(newModelEntity))) {
            E exisistModelEntity = repository.findOne(Example.of(newModelEntity)).orElse(newModelEntity);
            newModelEntity.setOldId(exisistModelEntity.getId());
            savedModelEntity = repository.save(newModelEntity);
        } else {
            savedModelEntity = repository.save(newModelEntity);
        }
        return assembler.toModel(savedModelEntity);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.I_AM_A_TEAPOT)).build();
    }
}

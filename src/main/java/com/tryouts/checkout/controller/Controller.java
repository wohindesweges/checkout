package com.tryouts.checkout.controller;

import com.tryouts.checkout.controller.exception.EntityNotFound;
import com.tryouts.checkout.dto.Dto;
import com.tryouts.checkout.entity.ModelEntity;
import com.tryouts.checkout.entity.exception.NotValid;
import com.tryouts.checkout.representation.assembler.Assembler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public abstract class Controller<E extends ModelEntity<D>, D extends Dto<E>, R extends JpaRepository<E, Long>, A extends Assembler<D, E>> {

    R repository;
    A assembler;


    public EntityModel<D> findByID(@PathVariable Long id) {
        final Optional<E> byId = this.repository.findById(id);
        E entityModel = byId.orElseThrow(() -> new EntityNotFound("Requested element not found", this));
        return assembler.toModel(entityModel.getDto());
    }

    public CollectionModel<EntityModel<D>> all() {
        return assembler.toCollectionModel(repository.findAll().stream().map(ModelEntity::getDto).toList());
    }

    public EntityModel<D> put(@RequestBody D dto) throws NotValid {
        E savedModelEntity = null;
        dto.getModelEntity().validate();
        savedModelEntity = repository.save(dto.getModelEntity());
        return assembler.toModel(savedModelEntity.getDto());
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.I_AM_A_TEAPOT)).build();
    }
}

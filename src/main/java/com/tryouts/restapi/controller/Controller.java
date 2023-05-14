package com.tryouts.restapi.controller;

import com.tryouts.restapi.controller.exception.EntityNotFound;
import com.tryouts.restapi.entity.ModelEntity;
import com.tryouts.restapi.entity.exception.NotValid;
import com.tryouts.restapi.representation.assembler.Assembler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public abstract class Controller<E extends ModelEntity, R extends JpaRepository<E, Long>, A extends Assembler<E>> {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    R repository;
    A assembler;


    public EntityModel<E> findNewestByID(@PathVariable Long id) {
        Optional<E> byId = this.repository.findById(id);
        if (byId.isPresent()) {
            while (byId.isPresent() && byId.get().getNewId() != null) { //TODO SQL ?
                byId = repository.findById(byId.get().getNewId());
            }
        }
        E entityModel = byId.orElseThrow(() -> new EntityNotFound("Requested element not found", this));
        return assembler.toModel(entityModel);
    }

    public EntityModel<E> findByID(@PathVariable Long id) {
        final Optional<E> byId = this.repository.findById(id);
        E entityModel = byId.orElseThrow(() -> new EntityNotFound("Requested element not found", this));
        return assembler.toModel(entityModel);
    }

    public CollectionModel<EntityModel<E>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }

    public EntityModel<E> put(@RequestBody E newModelEntity) throws NotValid {
        E savedModelEntity = null;
        newModelEntity.validate();
        //FIXME --> implementation of example+matcher missing to match by specific values to avoid duplicates
        // --> error during findAll()
        List<E> allEntities = repository.findAll(Example.of(newModelEntity), Sort.by(Sort.Direction.DESC, "id"));
        E existingModelEntity = null;
        if (!allEntities.isEmpty()) {
            existingModelEntity = allEntities.stream().findFirst().orElse(newModelEntity);
            newModelEntity.setOldId(existingModelEntity.getId());
        }
        savedModelEntity = repository.save(newModelEntity);
        if (existingModelEntity != null) {
            existingModelEntity.setNewId(savedModelEntity.getId());
            repository.save(existingModelEntity);
        }
        return assembler.toModel(savedModelEntity);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            //TODO recursive delete for historyElements
            repository.deleteById(id);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.I_AM_A_TEAPOT)).build();
    }
}

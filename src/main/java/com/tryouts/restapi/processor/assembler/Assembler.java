package com.tryouts.restapi.processor.assembler;

import com.tryouts.restapi.model.ModelEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public interface Assembler<E extends ModelEntity> extends RepresentationModelAssembler<E, EntityModel<E>> {

    @Override
    default EntityModel<E> toModel(E modelEntity) {
        return EntityModel.of(modelEntity, //
                linkTo(methodOn(modelEntity.getController()).findByID(modelEntity.getId())).withSelfRel(),
                linkTo(methodOn(modelEntity.getController()).all()).withRel(modelEntity.getAllRelationDiscription()));
    }
}

package com.tryouts.restapi.processor.assembler;

import com.tryouts.restapi.entity.ModelEntity;
import jakarta.annotation.Nonnull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public interface Assembler<E extends ModelEntity> extends RepresentationModelAssembler<E, EntityModel<E>> {
    @Nonnull
    @Override
    default EntityModel<E> toModel(@Nonnull E modelEntity) {
        ArrayList<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(modelEntity.getController()).findByID(modelEntity.getId())).withSelfRel());
        links.add(linkTo(methodOn(modelEntity.getController()).all()).withRel(modelEntity.getAllRelationDiscription()));
        if (modelEntity.getOldId() != 0L) {
            links.add(linkTo(methodOn(modelEntity.getController()).findByID(modelEntity.getOldId())).withRel("IDLastVersion"));
        }
        return EntityModel.of(modelEntity, links);
    }
}

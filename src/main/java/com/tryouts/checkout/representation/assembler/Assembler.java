package com.tryouts.checkout.representation.assembler;

import com.tryouts.checkout.dto.Dto;
import com.tryouts.checkout.entity.ModelEntity;
import jakarta.annotation.Nonnull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public interface Assembler<D extends Dto<E>, E extends ModelEntity<D>> extends RepresentationModelAssembler<D, EntityModel<D>> {
    @Nonnull
    @Override
    default EntityModel<D> toModel(@Nonnull D modelEntity) {
        ArrayList<Link> links = new ArrayList<>();
        if (modelEntity.getId() != null) {
            links.add(linkTo(methodOn(modelEntity.getController()).findByID(modelEntity.getId())).withSelfRel());
        }

        links.add(linkTo(methodOn(modelEntity.getController()).all()).withRel(modelEntity.getAllRelationDiscription()));
        return EntityModel.of(modelEntity, links);
    }
}

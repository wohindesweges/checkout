package com.tryouts.checkout.representation;

import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.dto.Dto;
import com.tryouts.dto.ErrorDto;
import com.tryouts.entity.ModelEntity;
import com.tryouts.entity.StockItem;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class  ErrorDTOProcessor<D extends Dto<E>, E extends ModelEntity<D> > implements RepresentationModelProcessor<EntityModel<ErrorDto<D,E>>> {

	@Override
	public EntityModel<ErrorDto<D, E>> process(EntityModel<ErrorDto<D, E>> model) {
		if (model.getContent() != null) {
			model.add(
					linkTo(methodOn(StockItemController.class).findByID(model.getContent().getId())).withRel(LinkRelation.of("TODO")) //
							.expand(model.getContent().getId()));

		}
		return model;
	}
}


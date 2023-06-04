package com.tryouts.checkout.representation.assembler;

import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.jpa.StockItemRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StockItemAssembler implements RepresentationModelAssembler<StockItemDto, EntityModel<StockItemDto>> {

	public EntityModel<StockItemDto> toModel(StockItemDto modelEntity) {
		ArrayList<Link> links = new ArrayList<>();
		if (modelEntity.getId() != null) {
			EntityModel<StockItemDto> invocationValue = methodOn(StockItemController.class).findByID(modelEntity.getId());
			 links.add(linkTo(invocationValue).withSelfRel());
		}
//		links.add(linkTo(methodOn(StockItemController.class).all()).withRel(modelEntity.getAllRelationDiscription()));
		return EntityModel.of(modelEntity, links);
	}

}

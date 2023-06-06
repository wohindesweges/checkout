package com.tryouts.checkout.representation.assembler;

import com.tryouts.checkout.controller.CheckOutController;
import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.dto.SumForCheckoutDto;
import com.tryouts.entity.StockItem;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CheckoutSumAssembler implements RepresentationModelAssembler<SumForCheckoutDto, EntityModel<SumForCheckoutDto>> {

	public EntityModel<SumForCheckoutDto> toModel(SumForCheckoutDto modelEntity) {
		ArrayList<Link> links = new ArrayList<>();
		links.add(linkTo(methodOn(CheckOutController.class).checkoutItems(modelEntity.getItems().stream().map(StockItem::getName).collect(Collectors.joining()))).withRel(
						LinkRelation.of("Checkout for items ")));
		return EntityModel.of(modelEntity, links);
	}

}

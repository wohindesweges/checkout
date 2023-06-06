package com.tryouts.checkout.representation.assembler;

import com.tryouts.checkout.controller.PricingRuleController;
import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.dto.PricingRuleDto;
import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.PricingRule;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PricingRuleAssembler implements RepresentationModelAssembler<PricingRuleDto, EntityModel<PricingRuleDto>> {

	public EntityModel<PricingRuleDto> toModel(PricingRuleDto modelEntity) {
		ArrayList<Link> links = new ArrayList<>();
		if (modelEntity.getId() != null) {
			EntityModel<PricingRuleDto> invocationValue = methodOn(PricingRuleController.class).findByID(modelEntity.getId());
			 links.add(linkTo(invocationValue).withSelfRel());
		}
		links.add(linkTo(methodOn(PricingRuleController.class).all()).withRel(modelEntity.getAllRelationDiscription()));
		return EntityModel.of(modelEntity, links);
	}

}

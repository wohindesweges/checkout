package com.tryouts.checkout.representation;

import com.tryouts.checkout.controller.CheckOutController;
import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.dto.SumForCheckoutDto;
import com.tryouts.entity.StockItem;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class SumForCheckoutProcessor implements RepresentationModelProcessor<EntityModel<SumForCheckoutDto>> {
    @NonNull
    @Override
    public EntityModel<SumForCheckoutDto> process(@NonNull EntityModel<SumForCheckoutDto> model) {
        if (model.getContent() != null) {
			final Set<StockItem> collect = model.getContent().getItems().stream().collect(Collectors.toSet());
			collect.forEach(stockItem -> {
				model.add(
						linkTo(methodOn(StockItemController.class).findByID(stockItem.getId())).withRel(LinkRelation.of("StockItem "+ stockItem.getName())) //
								.expand(stockItem.getId()));
			});

        }
        return model;
    }
}


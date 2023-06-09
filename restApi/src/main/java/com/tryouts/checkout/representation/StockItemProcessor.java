package com.tryouts.checkout.representation;

import com.tryouts.checkout.controller.CheckOutController;
import com.tryouts.checkout.controller.PricingRuleController;
import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.jpa.StockItemRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class StockItemProcessor implements RepresentationModelProcessor<EntityModel<StockItemDto>> {
    @NonNull
    @Override
    public EntityModel<StockItemDto> process(@NonNull EntityModel<StockItemDto> model) {
        if (model.getContent() != null) {
            model.add(
                    linkTo(methodOn(CheckOutController.class).scanItem(model.getContent().getName())).withRel(LinkRelation.of("ScanItem")) //
                            .expand(model.getContent().getId()));
        }
        return model;
    }
}


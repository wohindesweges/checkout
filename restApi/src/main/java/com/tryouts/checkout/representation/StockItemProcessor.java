package com.tryouts.checkout.representation;

import com.tryouts.checkout.controller.StockItemController;
import com.tryouts.entity.StockItem;
import com.tryouts.repository.jpa.StockItemRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class StockItemProcessor implements RepresentationModelProcessor<EntityModel<StockItem>> {
    @NonNull
    @Override
    public EntityModel<StockItem> process(@NonNull EntityModel<StockItem> model) {
        if (model.getContent() != null) {
            model.add(
                    linkTo(methodOn(StockItemController.class).findByID(model.getContent().getId())).withRel(LinkRelation.of("TODO")) //
                            .expand(model.getContent().getId()));

        }
        return model;
    }
}


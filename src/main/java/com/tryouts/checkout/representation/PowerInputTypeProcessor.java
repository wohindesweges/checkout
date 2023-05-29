package com.tryouts.checkout.representation;

import com.tryouts.checkout.entity.StockItem;
import com.tryouts.checkout.repository.StockItemRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PowerInputTypeProcessor implements RepresentationModelProcessor<EntityModel<StockItem>> {
    @NonNull
    @Override
    public EntityModel<StockItem> process(@NonNull EntityModel<StockItem> model) {
        if (model.getContent() != null) {
            model.add(
                    linkTo(methodOn(StockItemRepository.class).findById(model.getContent().getId())).withRel(LinkRelation.of("TODO")) //
                            .expand(model.getContent().getId()));

        }
        return model;
    }
}


package com.tryouts.restapi.processor;

import com.tryouts.restapi.controller.DistrictController;
import com.tryouts.restapi.controller.PowerInputTypeController;
import com.tryouts.restapi.entity.PowerInput;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PowerInputTypeProcessor implements RepresentationModelProcessor<EntityModel<PowerInput>> {
    @NonNull
    @Override
    public EntityModel<PowerInput> process(@NonNull EntityModel<PowerInput> model) {
        if (model.getContent() != null) {
            model.add(
                    linkTo(methodOn(PowerInputTypeController.class).findByID(model.getContent().getPowerInputType().getId())).withRel(LinkRelation.of("powerInputType")) //
                            .expand(model.getContent().getPowerInputType().getId()));
            model.add(
                    linkTo(methodOn(DistrictController.class).findByID(model.getContent().getDistrict().getId()))
                            .withRel(LinkRelation.of("district")));
        }
        return model;
    }
}


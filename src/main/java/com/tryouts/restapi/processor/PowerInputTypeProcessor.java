package com.tryouts.restapi.processor;

import com.tryouts.restapi.controller.DistrictController;
import com.tryouts.restapi.model.PowerInput;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PowerInputTypeProcessor implements RepresentationModelProcessor<EntityModel<PowerInput>> {

    @Override
    public EntityModel<PowerInput> process(EntityModel<PowerInput> model) {
        if (model.getContent() != null) {
            model.add(
                    Link.of("/powerInputType/{powerInputTypeID}").withRel(LinkRelation.of("powerInputType")) //
                            .expand(model.getContent().getPowerInputType().getId()));
            model.add(
                    linkTo(methodOn(DistrictController.class).findByID(model.getContent().getDistrict().getId()))
                            .withRel(LinkRelation.of("district")));
        }
        return model;
    }
}


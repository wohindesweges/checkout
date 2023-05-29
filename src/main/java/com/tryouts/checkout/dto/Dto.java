package com.tryouts.checkout.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tryouts.checkout.controller.Controller;
import com.tryouts.checkout.entity.ModelEntity;

public abstract class Dto<E extends ModelEntity> {
    Long id;

    @JsonIgnore
    public abstract Class<? extends Controller> getController();

    @JsonIgnore
    public abstract String getAllRelationDiscription();

    public abstract Long getId();

    public abstract E getModelEntity();
}

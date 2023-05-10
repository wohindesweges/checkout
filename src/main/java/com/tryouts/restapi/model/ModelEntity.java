package com.tryouts.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tryouts.restapi.controller.Controller;

public abstract class ModelEntity {
    @JsonIgnore
    public abstract String getAllRelationDiscription();

    public abstract Long getId();

    @JsonIgnore
    public abstract Class<? extends Controller> getController();
}

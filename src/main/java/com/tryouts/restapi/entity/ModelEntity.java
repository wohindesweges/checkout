package com.tryouts.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tryouts.restapi.controller.Controller;
import com.tryouts.restapi.entity.exception.NotValid;

public abstract class ModelEntity {
    long oldId;


    @JsonIgnore
    public abstract String getAllRelationDiscription();

    public abstract Long getId();

    public long getOldId() {
        return oldId;
    }

    public void setOldId(long oldId) {
        this.oldId = oldId;
    }

    @JsonIgnore
    public abstract Class<? extends Controller> getController();

    public abstract void validate() throws NotValid;
}

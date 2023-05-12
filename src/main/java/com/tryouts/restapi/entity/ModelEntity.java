package com.tryouts.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tryouts.restapi.controller.Controller;
import com.tryouts.restapi.entity.exception.NotValid;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ModelEntity {
    Long oldId;

	Long newId;


    @JsonIgnore
    public abstract String getAllRelationDiscription();

    public abstract Long getId();

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    @JsonIgnore
    public abstract Class<? extends Controller> getController();

    public abstract void validate() throws NotValid;

	public void setNewId(Long newID) {
		this.newId = newID;
	}

	public Long getNewId() {
		return newId;
	}
}

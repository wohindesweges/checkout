package com.tryouts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Dto<E> {
    Long id;


    @JsonIgnore
    public abstract String getAllRelationDiscription();

    public abstract Long getId();
	@JsonIgnore
    public abstract E getEntityModel();
}

package com.tryouts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tryouts.dto.Dto;
import com.tryouts.entity.exception.NotValid;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ModelEntity<D> {


    public abstract Long getId();


    public abstract void validate() throws NotValid;
	@JsonIgnore
    public abstract D getDto();


}

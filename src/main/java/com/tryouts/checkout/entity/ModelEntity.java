package com.tryouts.checkout.entity;

import com.tryouts.checkout.dto.Dto;
import com.tryouts.checkout.entity.exception.NotValid;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ModelEntity<D extends Dto> {


    public abstract Long getId();


    public abstract void validate() throws NotValid;

    public abstract D getDto();


}

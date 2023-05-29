package com.tryouts.checkout.entity.exception;

import com.tryouts.checkout.entity.ModelEntity;

public class NotValid extends RuntimeException {

    private final ModelEntity entity;

    public NotValid(String message, ModelEntity entity) {
        super(message);
        this.entity = entity;
    }

    public ModelEntity getEntity() {
        return entity;
    }
}

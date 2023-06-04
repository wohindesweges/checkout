package com.tryouts.entity.exception;

import com.tryouts.entity.ModelEntity;

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

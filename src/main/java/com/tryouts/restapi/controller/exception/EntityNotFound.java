package com.tryouts.restapi.controller.exception;

import com.tryouts.restapi.controller.Controller;

public class EntityNotFound extends RuntimeException {

    private final Controller controller;

    public <C extends Controller> EntityNotFound(String message, C controller) {
        super(message);
        this.controller = controller;
    }

    public Controller getModelEntity() {
        return controller;
    }
}

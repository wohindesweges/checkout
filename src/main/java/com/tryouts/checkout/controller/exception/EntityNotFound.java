package com.tryouts.checkout.controller.exception;

import com.tryouts.checkout.controller.Controller;

public class EntityNotFound extends RuntimeException {

    private final Controller controller;

    public <C extends Controller> EntityNotFound(String message, C controller) {
        super(message);
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }
}

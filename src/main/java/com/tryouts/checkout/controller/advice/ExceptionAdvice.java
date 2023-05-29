package com.tryouts.checkout.controller.advice;

import com.tryouts.checkout.controller.exception.EntityNotFound;
import com.tryouts.checkout.entity.exception.NotValid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String entityNotFound(EntityNotFound ex) {
        return ex.getController().getClass().getSimpleName() + " " + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotValid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String newEntityNotValid(NotValid ex) {
        return ex.getEntity().getClass().getSimpleName() + ": " + ex.getMessage();
    }
}

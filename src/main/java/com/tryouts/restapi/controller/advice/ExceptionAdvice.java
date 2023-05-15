package com.tryouts.restapi.controller.advice;

import com.tryouts.restapi.controller.exception.EntityNotFound;
import com.tryouts.restapi.entity.exception.NotValid;
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
        return ex.getModelEntity().getClass().getSimpleName() + " " + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotValid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String newEntityNotValid(NotValid ex) {
        return ex.getEntity().getClass().getSimpleName() + ": " + ex.getMessage();
    }
}

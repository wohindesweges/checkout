package com.tryouts.checkout.controller.advice;
import com.tryouts.dto.Dto;
import com.tryouts.entity.exception.NotValid;
import com.tryouts.repository.exception.EntityAlreadyExsists;
import com.tryouts.repository.exception.EntityNotFound;
import org.springframework.hateoas.EntityModel;
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
    String entityNotFound(EntityNotFound exception) {
        return  exception.getMessage();
    }

	@ResponseBody
	@ExceptionHandler(EntityAlreadyExsists.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	EntityModel<? extends Dto<?>> entityAlreadyExists(EntityAlreadyExsists exception) {
		return  EntityModel.of(exception.getExistsingEntity());
	}

    @ResponseBody
    @ExceptionHandler(NotValid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String newEntityNotValid(NotValid exception) {
        return exception.getEntity().getClass().getSimpleName() + ": " + exception.getMessage();
    }
}

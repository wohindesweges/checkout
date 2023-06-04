package com.tryouts.repository.exception;

import org.springframework.lang.NonNull;

public class EntityNotFound extends RuntimeException {

	public EntityNotFound(@NonNull String message) {
		super(message);
	}
}

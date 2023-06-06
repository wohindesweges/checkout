package com.tryouts.repository.exception;

import com.tryouts.dto.Dto;
import com.tryouts.dto.ErrorDto;
import com.tryouts.entity.ModelEntity;

public class EntityAlreadyExsists extends RuntimeException {

	private Dto<?> existsingEntity;


	public EntityAlreadyExsists(ModelEntity<?> entity) {
		super("Entity already exists with ID "+ entity.getId());
		this.existsingEntity = entity.getDto();
	}

	public Dto<?> getExistsingEntity() {
		return new ErrorDto<>(existsingEntity, "Entity already exisist");
	}

}

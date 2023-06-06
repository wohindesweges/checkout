package com.tryouts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tryouts.entity.ModelEntity;

public class ErrorDto<D extends Dto<E>, E extends ModelEntity<D> > extends Dto<D> {

	String message;

	Dto<E> dto;

	public ErrorDto(Dto<?> dto, String message) {
		this.dto = (Dto<E>) dto;
		this.message=message;
	}

	@Override
	public String getAllRelationDiscription() {
		return "";
	}
	@JsonIgnore
	@Override
	public Long getId() {
		return dto.getId();
	}

	public String getMessage() {
		return message;
	}

	@Override
	public D getEntityModel() {
		return (D) dto;
	}

	public D getExistingEntity(){
		return (D) dto;
	}

}

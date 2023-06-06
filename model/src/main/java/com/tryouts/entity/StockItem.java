package com.tryouts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.exception.NotValid;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Entity
public class StockItem extends ModelEntity<StockItemDto> {
	@Id
	@GeneratedValue
	Long id;
	String name;

	String description;

	public StockItem() {
	}


	@Override
	public Long getId() {
		return id;
	}

	public StockItem setId(Long id) {
		this.id = id;
		return this;
	}

	@Override
	public void validate() throws NotValid {
		if (!StringUtils.hasText(name)) {
			throw new NotValid("Name must be set", new StockItem());
		}
	}

	@JsonIgnore
	@Override
	public StockItemDto getDto() {
		return new StockItemDto().setName(this.name).setId(this.id);
	}

	public String getName() {
		return name;
	}

	public StockItem setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public StockItem setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public void updateByDto(StockItemDto dto) {
		if (StringUtils.hasText(dto.getName())) {
			this.name = dto.getName();
		}
		this.description = dto.getDescription();

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StockItem stockItem = (StockItem) o;
		return Objects.equals(id, stockItem.id) && Objects.equals(name, stockItem.name) && Objects.equals(description, stockItem.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description);
	}
}

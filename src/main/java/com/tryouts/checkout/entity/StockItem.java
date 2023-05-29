package com.tryouts.checkout.entity;

import com.tryouts.checkout.dto.StockItemDto;
import com.tryouts.checkout.entity.exception.NotValid;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.util.StringUtils;

@Entity
public class StockItem extends ModelEntity<StockItemDto> {
    @Id
    @GeneratedValue
    Long id;
    String name;

    public static StockItem empty() {
        return new StockItem();
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
        if (StringUtils.hasText(name)) {
            throw new NotValid("Name for Price must be set", new StockItem());
        }
    }

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
}

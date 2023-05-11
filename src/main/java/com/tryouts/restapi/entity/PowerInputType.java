package com.tryouts.restapi.entity;

import com.tryouts.restapi.controller.Controller;
import com.tryouts.restapi.controller.PowerInputTypeController;
import com.tryouts.restapi.entity.exception.NotValid;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class PowerInputType extends ModelEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String term;

    private String description;

    public PowerInputType(String term) {
        this.term = term;
    }

    public PowerInputType() {

    }


    @Override
    public String getAllRelationDiscription() {
        return "allPowerInputTypes";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Class<? extends Controller> getController() {
        return PowerInputTypeController.class;
    }

    @Override
    public void validate() throws NotValid {
        if (term == null) {
            throw new NotValid("Missing value: term", this);
        }
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerInputType that = (PowerInputType) o;
        return Objects.equals(id, that.id) && Objects.equals(term, that.term) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, term, description);
    }
}

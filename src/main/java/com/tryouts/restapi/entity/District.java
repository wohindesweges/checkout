package com.tryouts.restapi.entity;


import com.tryouts.restapi.controller.Controller;
import com.tryouts.restapi.controller.DistrictController;
import com.tryouts.restapi.entity.exception.NotValid;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class District extends ModelEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;


    public District() {

    }

    public District(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Class<? extends Controller> getController() {
        return DistrictController.class;
    }

    @Override
    public void validate() throws NotValid {
        if (name == null) {
            throw new NotValid("Missing value: name", this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public String getAllRelationDiscription() {
        return "allDistricts";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(name, district.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

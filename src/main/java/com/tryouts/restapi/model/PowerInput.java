package com.tryouts.restapi.model;


import com.tryouts.restapi.controller.Controller;
import com.tryouts.restapi.controller.PowerInputController;
import jakarta.persistence.*;

import java.time.Year;

@Entity
public class PowerInput extends ModelEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Double powerInput;

    private Year yearOfData;

    private boolean wholeCity;
    @ManyToOne(fetch = FetchType.EAGER)//TODO erklärung
    @JoinColumn(name = "powerInputID")
    private PowerInputType powerInputType;
    @ManyToOne(fetch = FetchType.EAGER)//TODO erklärung
    @JoinColumn(name = "districtID")
    private District district;

    public PowerInput() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Class<? extends Controller> getController() {
        return PowerInputController.class;
    }

    public Double getPowerinput() {
        return powerInput;
    }

    public void setPowerinput(Double powerinput) {
        this.powerInput = powerinput;
    }

    public Year getYearOfData() {
        return yearOfData;
    }

    public void setYearOfData(Year year) {
        this.yearOfData = year;
    }

    public Double getPowerInput() {
        return powerInput;
    }

    public void setPowerInput(Double powerInput) {
        this.powerInput = powerInput;
    }

    public boolean isWholeCity() {
        return wholeCity;
    }

    public void setWholeCity(boolean wholeCity) {
        this.wholeCity = wholeCity;
    }

    public PowerInputType getPowerInputType() {
        return powerInputType;
    }

    public void setPowerInputType(PowerInputType powerInputTypeId) {
        this.powerInputType = powerInputTypeId;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }


    @Override
    public String getAllRelationDiscription() {
        return "allPowerInputs";
    }


}

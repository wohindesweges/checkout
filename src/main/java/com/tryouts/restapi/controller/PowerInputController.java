package com.tryouts.restapi.controller;

import com.tryouts.restapi.model.PowerInput;
import com.tryouts.restapi.processor.assembler.PowerInputAssembler;
import com.tryouts.restapi.repo.PowerInputRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerInputController extends Controller<PowerInput, PowerInputRepository, PowerInputAssembler> {


    public PowerInputController(PowerInputRepository powerInputRepository, PowerInputAssembler assembler) {
        this.repository = powerInputRepository;
        this.assembler = assembler;
    }

    @Override
    @GetMapping("/powerInput/{id}")
    public EntityModel<PowerInput> findByID(@PathVariable Long id) {
        return super.findByID(id);
    }

    @Override
    @GetMapping("/powerInputs")
    public CollectionModel<EntityModel<PowerInput>> all() {
        return super.all();
    }
}

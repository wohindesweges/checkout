package com.tryouts.restapi.controller;

import com.tryouts.restapi.model.PowerInputType;
import com.tryouts.restapi.processor.assembler.PowerInputTypeAssembler;
import com.tryouts.restapi.repo.PowerInputTypeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerInputTypeController extends Controller<PowerInputType, PowerInputTypeRepository, PowerInputTypeAssembler> {


    public PowerInputTypeController(PowerInputTypeRepository powerInputRepository, PowerInputTypeAssembler assembler) {
        this.repository = powerInputRepository;
        this.assembler = assembler;
    }

    @Override
    @GetMapping("/powerInputType/{id}")
    public EntityModel<PowerInputType> findByID(@PathVariable Long id) {
        return super.findByID(id);
    }

    @Override
    @GetMapping("/powerInputTypes")
    public CollectionModel<EntityModel<PowerInputType>> all() {
        return super.all();
    }
}

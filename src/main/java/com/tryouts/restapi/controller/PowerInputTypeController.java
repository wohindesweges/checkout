package com.tryouts.restapi.controller;

import com.tryouts.restapi.entity.PowerInputType;
import com.tryouts.restapi.processor.assembler.PowerInputTypeAssembler;
import com.tryouts.restapi.repo.PowerInputTypeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerInputTypeController extends Controller<PowerInputType, PowerInputTypeRepository, PowerInputTypeAssembler> {
    private static final String pathRoot = "powerInputType";

    public PowerInputTypeController(PowerInputTypeRepository powerInputRepository, PowerInputTypeAssembler assembler) {
        this.repository = powerInputRepository;
        this.assembler = assembler;
    }

    @Override
    @GetMapping("/" + pathRoot + "/{id}")
    public EntityModel<PowerInputType> findByID(@PathVariable Long id) {
        return super.findByID(id);
    }

    @Override
    @GetMapping("/" + pathRoot)
    public CollectionModel<EntityModel<PowerInputType>> all() {
        return super.all();
    }

    @Override
    @PostMapping("/" + pathRoot)
    public EntityModel<PowerInputType> put(PowerInputType newModelEntity) {
        return super.put(newModelEntity);
    }
}

package com.tryouts.restapi.controller;

import com.tryouts.restapi.entity.PowerInput;
import com.tryouts.restapi.processor.assembler.PowerInputAssembler;
import com.tryouts.restapi.repo.PowerInputRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PowerInputController extends Controller<PowerInput, PowerInputRepository, PowerInputAssembler> {
    private static final String pathRoot = "powerInput";

    public PowerInputController(PowerInputRepository powerInputRepository, PowerInputAssembler assembler) {
        this.repository = powerInputRepository;
        this.assembler = assembler;
    }

    @Override
    @GetMapping("/" + pathRoot + "/{id}")
    public EntityModel<PowerInput> findByID(@PathVariable Long id) {
        return super.findByID(id);
    }

    @Override
    @GetMapping("/" + pathRoot)
    public CollectionModel<EntityModel<PowerInput>> all() {
        return super.all();
    }

    @Override
    @PostMapping("/" + pathRoot)
    public EntityModel<PowerInput> put(PowerInput newModelEntity) {
        return super.put(newModelEntity);
    }

    @Override
    @DeleteMapping("/" + pathRoot)
    public ResponseEntity<?> delete(Long id) {
        return super.delete(id);
    }
}

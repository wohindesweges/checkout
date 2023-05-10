package com.tryouts.restapi.controller;

import com.tryouts.restapi.model.District;
import com.tryouts.restapi.processor.assembler.DistrictAssembler;
import com.tryouts.restapi.repo.DistrictRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController extends Controller<District, DistrictRepository, DistrictAssembler> {

    public DistrictController(@Qualifier("small") DistrictRepository districtRepositoryRepo, DistrictAssembler districtAssembler) {
        this.repository = districtRepositoryRepo;
        this.assembler = districtAssembler;
    }

    /* example
    @GetMapping("/districts")
    @Override
    public CollectionModel<EntityModel<District>> all() {
        List<EntityModel<District>> employees = repository.findAll().stream()
                .map(district -> EntityModel.of(district,
                        linkTo(methodOn(DistrictController.class).findByID(district.getId())).withSelfRel(),
                        linkTo(methodOn(DistrictController.class).all()).withRel("district")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(DistrictController.class).all()).withSelfRel());
    }
    */
    @GetMapping("/districts")
    @Override
    public CollectionModel<EntityModel<District>> all() {
        return super.all();
    }


    /*
    @GetMapping("/district/{id}")
    @Override
    public EntityModel<District> findByID(@PathVariable Long id) {
        District district = this.districtRepositoryRepo.findById(id).orElseThrow(() -> new ServerErrorException("ONLY:TEST", null));
        return EntityModel.of(district, //
                linkTo(methodOn(DistrictController.class).findByID(id)).withSelfRel(),
                linkTo(methodOn(DistrictController.class).all()).withRel("district"));
    }
     */
    @GetMapping("/district/{id}")
    @Override
    public EntityModel<District> findByID(@PathVariable Long id) {
        return super.findByID(id);
    }


}

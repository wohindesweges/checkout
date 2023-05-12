package com.tryouts.restapi.controller;

import com.tryouts.restapi.entity.District;
import com.tryouts.restapi.processor.assembler.DistrictAssembler;
import com.tryouts.restapi.repo.DistrictRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DistrictController extends Controller<District, DistrictRepository, DistrictAssembler> {
    private static final String pathRoot = "district";

    public DistrictController( DistrictRepository repository, DistrictAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
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
    @GetMapping("/" + pathRoot)
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
    @GetMapping("/" + pathRoot + "/{id}")
    @Override
    public EntityModel<District> findByID(@PathVariable Long id) {
        return super.findByID(id);
    }
	@GetMapping("/" + pathRoot + "/{id}/newest")
	public EntityModel<District> findByIDNewest(@PathVariable Long id) {
		return super.findNewestByID(id);
	}

    @Override
    @PostMapping("/" + pathRoot)
    public EntityModel<District> put(@RequestBody District newDistrict) {
        return super.put(newDistrict);
    }

    @Override
    @DeleteMapping("/" + pathRoot)
    public ResponseEntity<?> delete(Long id) {
        return super.delete(id);
    }
}

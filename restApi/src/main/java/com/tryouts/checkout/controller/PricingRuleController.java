package com.tryouts.checkout.controller;

import com.tryouts.checkout.representation.assembler.PricingRuleAssembler;
import com.tryouts.domain.businessLogic.PricingActions;
import com.tryouts.dto.PricingRuleDto;
import com.tryouts.dto.StockItemDto;
import com.tryouts.entity.PricingRule;
import com.tryouts.entity.exception.NotValid;
import com.tryouts.repository.exception.EntityAlreadyExsists;
import com.tryouts.repository.exception.EntityNotFound;
import com.tryouts.repository.jpa.PricingRuleRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@ComponentScan(basePackages = { "com.tryouts.*" })
@RestController
public class PricingRuleController {
	private static final String pathRoot = "pricingrule";
	private final PricingActions pricingActions;
	private final PricingRuleRepository repository;
	private final PricingRuleAssembler pricingRuleAssembler;

	public PricingRuleController(PricingActions pricingActions, PricingRuleRepository pricingRuleRepository, PricingRuleAssembler pricingRuleAssembler) {
		this.pricingActions = pricingActions;
		this.repository = pricingRuleRepository;
		this.pricingRuleAssembler = pricingRuleAssembler;
	}

	@GetMapping("/" + pathRoot + "/{id}")
	public EntityModel<PricingRuleDto> findByID(@PathVariable Long id) {
		final Optional<PricingRule> byId = repository.findById(id);
		PricingRule entityModel = byId.orElseThrow(() -> new EntityNotFound("Requested element not found"));
		return pricingRuleAssembler.toModel(entityModel.getDto());
	}


	@GetMapping("/" + pathRoot)
	public CollectionModel<EntityModel<PricingRuleDto>> all() {
		return pricingRuleAssembler.toCollectionModel(repository.findAll().stream().map(PricingRule::getDto).toList());
	}

	@PutMapping("/" + pathRoot)
	public ResponseEntity<?> put(@RequestBody PricingRuleDto dto) throws NotValid {
		try {
			final PricingRuleDto stockItemDto = pricingActions.setNewPricingRule(dto);
			return new ResponseEntity<>(stockItemDto, HttpStatus.OK);
		} catch (EntityAlreadyExsists entityAlreadyExsists) {
			return new ResponseEntity<>(entityAlreadyExsists.getExistsingEntity(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/" + pathRoot + "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.I_AM_A_TEAPOT)).build();
	}

}

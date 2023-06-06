package com.tryouts.checkout.controller;

import com.tryouts.checkout.representation.assembler.CheckoutSumAssembler;
import com.tryouts.domain.businessLogic.CheckoutActions;
import com.tryouts.dto.SumForCheckoutDto;
import com.tryouts.entity.StockItem;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ComponentScan(basePackages={"com.tryouts.*"})
		@RestController
public class CheckOutController {
    private static final String pathRoot = "checkout";
    private final CheckoutActions checkoutActions;
	private final CheckoutSumAssembler checkoutSumAssembler ;

	public CheckOutController(CheckoutActions checkoutActions, CheckoutSumAssembler checkoutSumAssembler) {
        this.checkoutActions = checkoutActions;
		this.checkoutSumAssembler = checkoutSumAssembler;
	}


	@GetMapping("/" + pathRoot + "/")
	public EntityModel<SumForCheckoutDto> checkoutItems(@RequestParam(value="items") String items) {
		Arrays.stream(items.split("")).forEach(checkoutActions::scanItem);
		final double currentTotal = checkoutActions.getCurrentTotal();
		final List<StockItem> scannedItems = new ArrayList<>(checkoutActions.getScannedItems());
		checkoutActions.finishCheckout();

		return checkoutSumAssembler.toModel(new SumForCheckoutDto(currentTotal, scannedItems));
	}
	@PostMapping("/" + pathRoot + "/scan")
	public ResponseEntity<HttpStatus> scanItem(@RequestParam(value="itemName") String name) {
		checkoutActions.scanItem(name);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}

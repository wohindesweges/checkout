package com.tryouts.restapi.controller;

import com.tryouts.restapi.parser.KWKParser;
import com.tryouts.restapi.repository.DistrictRepository;
import com.tryouts.restapi.repository.PowerInputRepository;
import com.tryouts.restapi.repository.PowerInputTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataInputController {


    private final KWKParser kwkParser;
    private final DistrictRepository districtRepository;
    private final PowerInputRepository powerInputRepository;
    private final PowerInputTypeRepository powerInputTypeRepository;

    public DataInputController(KWKParser kwkParser,//
                               DistrictRepository districtRepository,//
                               PowerInputRepository powerInputRepository, //
                               PowerInputTypeRepository powerInputTypeRepository) {
        this.kwkParser = kwkParser;
        this.districtRepository = districtRepository;
        this.powerInputRepository = powerInputRepository;
        this.powerInputTypeRepository = powerInputTypeRepository;
    }

    @GetMapping("/initKWKData")
    ResponseEntity<?> collectData() {
        kwkParser.readInput();
        kwkParser.parse();
        powerInputTypeRepository.saveAll(kwkParser.getPowerInputType());
        districtRepository.saveAll(kwkParser.getDistricts());
        powerInputRepository.saveAll(kwkParser.getPowerInputs());
        return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.CREATED)).build();
    }


}

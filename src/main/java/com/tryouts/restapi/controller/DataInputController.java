package com.tryouts.restapi.controller;

import com.tryouts.restapi.entity.PowerInputType;
import com.tryouts.restapi.parser.DistrictDataParser;
import com.tryouts.restapi.repository.DistrictRepository;
import com.tryouts.restapi.repository.PowerInputRepository;
import com.tryouts.restapi.repository.PowerInputTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class DataInputController {


    private final DistrictDataParser districtDataParser;
    private final DistrictRepository districtRepository;
    private final PowerInputRepository powerInputRepository;
    private final PowerInputTypeRepository powerInputTypeRepository;

    public DataInputController(DistrictDataParser districtDataParser,//
                               DistrictRepository districtRepository,//
                               PowerInputRepository powerInputRepository, //
                               PowerInputTypeRepository powerInputTypeRepository) {
        this.districtDataParser = districtDataParser;
        this.districtRepository = districtRepository;
        this.powerInputRepository = powerInputRepository;
        this.powerInputTypeRepository = powerInputTypeRepository;
    }

    @GetMapping("/initCogenerationData")
    ResponseEntity<?> collectDataKWK() {
        URI uri;
        try {
            uri = new URI("https", "fbinter.stadt-berlin.de", "/fb/wfs/data/senstadt/s_kwk_strom_bez", "version=2.0.0&typenames=s_kwk_strom_bez&request=getfeature&service=wfs", "");
        } catch (URISyntaxException e) {
            return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
        return getResponse(uri, "Cogeneration-Heat");
    }

    private ResponseEntity<?> getResponse(URI uri, String typeName) {
        districtDataParser.setUri(uri);
        districtDataParser.setPowerInputType(new PowerInputType(typeName));
        districtDataParser.readInput();
        districtDataParser.parse();
        powerInputTypeRepository.saveAll(districtDataParser.getPowerInputType());
        districtRepository.saveAll(districtDataParser.getDistricts());
        powerInputRepository.saveAll(districtDataParser.getPowerInputs());
        districtDataParser.clearData();
        return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.CREATED)).build();
    }


    @GetMapping("/initBiomassData")
    ResponseEntity<?> collectDataBiomasse() {
        URI uri;
        try {
            uri = new URI("https", "fbinter.stadt-berlin.de", "/fb/wfs/data/senstadt/s_biom_einspstrom", "version=2.0.0&typenames=s_biom_einspstrom_bez&request=getfeature&service=wfs", "");
        } catch (URISyntaxException e) {
            return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
        return getResponse(uri, "biomass");
    }


}

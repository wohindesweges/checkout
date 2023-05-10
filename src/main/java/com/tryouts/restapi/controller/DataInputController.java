package com.tryouts.restapi.controller;

import com.tryouts.restapi.parser.KWKParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataInputController {


    private final KWKParser kwkParser;

    public DataInputController(KWKParser kwkParser) {
        this.kwkParser = kwkParser;
    }

    @GetMapping("/collectData")
    void collectData() {
        kwkParser.readInput();
        kwkParser.parse();
        kwkParser.saveData(); // TODO --> sava data not in parser
        //TODO KWKPARSER RESET;
    }


}

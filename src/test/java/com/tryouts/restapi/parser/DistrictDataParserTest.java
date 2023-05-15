package com.tryouts.restapi.parser;

import com.tryouts.restapi.dataAdapter.WsfAdapter;
import com.tryouts.restapi.entity.PowerInputType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

@ExtendWith(MockitoExtension.class)
class DistrictDataParserTest {
    Logger LOG = LogManager.getLogger(DistrictDataParser.class);
    @Mock
    WsfAdapter wsfAdapter;

    @InjectMocks
    DistrictDataParser districtDataParser;


    @Test
    public void readCogenerationFullXML() throws URISyntaxException {
        Mockito.when(wsfAdapter.getData()).thenReturn(readTestXML("./src/test/resources/kwkData.xml"));
        districtDataParser.readInput();
        districtDataParser.setDistrictInputElementName("s_kwk_strom_bez");
        districtDataParser.setPowerInputType(new PowerInputType("TESTDATA"));
        districtDataParser.parse();
        Assertions.assertEquals(12, districtDataParser.getDistricts().size());
    }


    @Test
    public void readBiomassFullXML() throws URISyntaxException {
        Mockito.when(wsfAdapter.getData()).thenReturn(readTestXML("./src/test/resources/biomData.xml"));
        districtDataParser.readInput();
        districtDataParser.setDistrictInputElementName("s_biom_einspstrom");
        districtDataParser.setPowerInputType(new PowerInputType("TESTDATA"));
        districtDataParser.parse();
        Assertions.assertEquals(12, districtDataParser.getDistricts().size());
    }

    private byte[] readTestXML(String filePath) {
        try (InputStream is = new FileInputStream(filePath)) {
            return is.readAllBytes();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
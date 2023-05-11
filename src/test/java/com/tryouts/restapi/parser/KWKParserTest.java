package com.tryouts.restapi.parser;

import com.tryouts.restapi.dataAdapter.WsfAdapter;
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

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class KWKParserTest {
    Logger LOG = LogManager.getLogger(KWKParser.class);
    @Mock
    WsfAdapter wsfAdapter;

    @InjectMocks
    KWKParser kwkParser;


    @Test
    public void readFullXML() {
        Mockito.when(wsfAdapter.getData()).thenReturn(readTestXML());
        kwkParser.readInput();
        kwkParser.parse();
        kwkParser.getDistricts();
        Assertions.assertTrue(true);
    }

    private byte[] readTestXML() {
        try (InputStream is = new FileInputStream("./src/test/resources/kwkData.xml")) {
            return is.readAllBytes();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
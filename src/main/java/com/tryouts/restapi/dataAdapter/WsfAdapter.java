package com.tryouts.restapi.dataAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class WsfAdapter extends DataAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(WsfAdapter.class);

    @Override
    public byte[] getData() {
        return readTestXML();
    }

    private byte[] readTestXML() {

        //TODO Connect TO WSF API
        try (InputStream is = new FileInputStream("./src/test/resources/kwkData.xml")) {
            return is.readAllBytes();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}


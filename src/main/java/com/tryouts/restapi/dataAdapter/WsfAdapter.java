package com.tryouts.restapi.dataAdapter;

import com.tryouts.restapi.dataClient.SimpleHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class WsfAdapter extends DataAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(WsfAdapter.class);
    private final SimpleHttpClient simpleHttpClient;
    private URI uri;


    public WsfAdapter(SimpleHttpClient simpleHttpClient) {
        this.simpleHttpClient = simpleHttpClient;
    }


    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    @Override
    public byte[] getData() throws URISyntaxException {
        return simpleHttpClient.requestDataFromURI(uri);
    }

}


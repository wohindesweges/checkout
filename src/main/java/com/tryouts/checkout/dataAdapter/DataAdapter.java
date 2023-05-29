package com.tryouts.checkout.dataAdapter;


import java.net.URISyntaxException;

// Adapter to retrieve Data from external Endpoint
public abstract class DataAdapter {
    public abstract byte[] getData() throws URISyntaxException;
}

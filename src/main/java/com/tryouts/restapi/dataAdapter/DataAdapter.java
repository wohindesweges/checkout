package com.tryouts.restapi.dataAdapter;


import java.net.URISyntaxException;

public abstract class DataAdapter {
    public abstract byte[] getData() throws URISyntaxException;
}

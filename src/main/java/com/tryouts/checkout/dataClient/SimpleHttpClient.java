package com.tryouts.checkout.dataClient;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class SimpleHttpClient {

    public byte[] requestDataFromURI(URI uri) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
            HttpRequest request = requestBuilder
                    .uri(uri)
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .GET()
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().getBytes();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

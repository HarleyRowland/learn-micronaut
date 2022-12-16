package com.hr17;

import com.fasterxml.jackson.databind.JsonNode;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloWorldControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void helloWorldRespondsWithProperContent() {
        String response = client.toBlocking().retrieve("/hello");
        assertEquals(response, "Hello from Service");
    }

    @Test
    void helloWorldRespondsWithProperContentAndResponseCode() {
        HttpResponse<String> response = client.toBlocking().exchange("/hello", String.class);
        assertEquals(response.body(), "Hello from Service");
        assertEquals(response.getStatus(), HttpStatus.OK);
    }

    @Test
    void helloFromConfigEndpointReturnsMessageFromConfigFile() {
        HttpResponse<String> response = client.toBlocking().exchange("/hello/config", String.class);
        assertEquals(response.body(), "Hello from application.yml");
        assertEquals(response.getStatus(), HttpStatus.OK);
    }

    @Test
    void helloFromTranslationEndpointReturnsContentFromConfigFile() {
        HttpResponse<JsonNode> response = client.toBlocking().exchange("/hello/translation", JsonNode.class);
        assertEquals(response.body().toString(), "{\"en\":\"Hello World\",\"de\":\"Hallo Welt\"}");
        assertEquals(response.getStatus(), HttpStatus.OK);
    }
}

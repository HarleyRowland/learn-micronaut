package com.hr17.hello;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Primary
@Singleton
public class HelloWorldService implements MyService {

    public String helloFromService(){
        return "Hello from Service";
    }

}

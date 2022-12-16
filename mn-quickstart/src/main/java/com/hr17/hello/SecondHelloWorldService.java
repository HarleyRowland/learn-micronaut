package com.hr17.hello;

import jakarta.inject.Singleton;

@Singleton
public class SecondHelloWorldService implements MyService {

    public String helloFromService(){
        return "Hello from Second Service";
    }

}

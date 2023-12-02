package com.idp.idpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IdpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdpServerApplication.class, args);
    }

}

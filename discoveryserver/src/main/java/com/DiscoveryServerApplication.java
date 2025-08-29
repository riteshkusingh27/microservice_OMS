package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaServer   // creates a discovery server
@ComponentScan(basePackages = {"com", "config"})
public class DiscoveryServerApplication {
    public static void main(String[] args){
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}

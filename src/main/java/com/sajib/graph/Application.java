package com.sajib.graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sajib on 2/18/19.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.sajib.graph"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

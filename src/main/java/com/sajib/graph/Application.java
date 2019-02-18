package com.sajib.graph;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by sajib on 2/18/19.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.sajib.graph"})
@EntityScan(basePackages = {"com.sajib.graph.entity"})
@EnableJpaRepositories(basePackages={"com.sajib.graph.dao"})
public class Application {
    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
        return hemf.getSessionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

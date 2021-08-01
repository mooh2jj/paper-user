package com.example.paperuser.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.example.paperuser.user")
@EnableJpaRepositories(basePackages = {
        "com.example.paperuser.user.repository"
})
@EntityScan(basePackages = {
        "com.example.paperuser.user.domain"
})
public class PaperUserModule {

}

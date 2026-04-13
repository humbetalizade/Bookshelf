package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,           // Security login OFF
        WebMvcAutoConfiguration.class,             // WebConfig problemi OFF
        SpringDataWebAutoConfiguration.class       // JPA Web problemi OFF
})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
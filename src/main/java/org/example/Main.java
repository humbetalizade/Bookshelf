package org.example;

import org.example.service.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,           // Security login OFF
        WebMvcAutoConfiguration.class,             // WebConfig problemi OFF
        SpringDataWebAutoConfiguration.class       // JPA Web problemi OFF
})
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(Main.class, args);

    }
    @Bean
    public CommandLineRunner run(BookService bookService) {
        return args -> {
            bookService.importBooksFromAlinino();
        };
    }

}
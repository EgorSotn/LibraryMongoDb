package org.example.home;

import org.example.home.service.LibraryService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableMongoRepositories
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        LibraryService bookService = context.getBean(LibraryService.class);

       // bookService.test();




    }
}

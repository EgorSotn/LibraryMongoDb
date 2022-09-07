package org.example.home.repository;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"java.org.example.home.config", "java.org.example.home.repository"})
public class AbstractRepositoryMongoTest {
}

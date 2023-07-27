package ru.otus.library.security;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Entry point to the application. Creates Application context
 */
@EnableConfigurationProperties
@EnableMongoRepositories
@EnableMongock
@SpringBootApplication
public class Spring12Application {
  public static void main(String[] args) {
    SpringApplication.run(Spring12Application.class, args);
  }

}

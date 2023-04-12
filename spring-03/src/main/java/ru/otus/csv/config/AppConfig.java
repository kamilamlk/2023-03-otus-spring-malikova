package ru.otus.csv.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Common application configuration.
 */
@Configuration
@EnableConfigurationProperties(QuizProperties.class)
public class AppConfig {
}

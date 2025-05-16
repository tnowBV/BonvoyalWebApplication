package com.bonvoyal.templates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * <p>This class bootstraps the application using {@link SpringApplication}</p>
 *
 * @author Terrell
 */
@SpringBootApplication
public class GradleApplication {

    /**
     * The main method which serves as the entry point of the application.
     *
     * @param args Command-line arguments passed during application startup.
     */
    public static void main(final String[] args) {
        SpringApplication.run(GradleApplication.class, args);
    }

}

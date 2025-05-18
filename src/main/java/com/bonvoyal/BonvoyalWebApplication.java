package com.bonvoyal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The entry point of the Spring Boot application.
 *
 * <p>This class bootstraps the application using {@link SpringApplication}</p>
 *
 * @author tnowBV
 */
@SpringBootApplication
public class BonvoyalWebApplication {

    /**
     * The main method which serves as the entry point of the application.
     *
     * @param args Command-line arguments passed during application startup.
     */
    public static void main(final String[] args) {
        SpringApplication.run(BonvoyalWebApplication.class, args);
    }

}

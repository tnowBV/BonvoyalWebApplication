package com.bonvoyal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The entry point of the Spring Boot application.
 *
 * <p>This class bootstraps the application using {@link SpringApplication}</p>
 *
 * @author tnowBV
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
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

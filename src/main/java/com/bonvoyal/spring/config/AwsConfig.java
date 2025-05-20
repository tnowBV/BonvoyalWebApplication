package com.bonvoyal.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

/**
 * AWS configuration class responsible for initializing AWS SDK clients.
 *
 * <p>Ensure the AWS SDK is configured with valid credentials using one of the supported
 * methods (e.g., environment variables, credential profiles, or IAM roles).</p>
 *
 * @author TnowBV
 */
@Configuration
public class AwsConfig {

    /**
     * Creates and configures an Amazon SNS client bean.
     *
     * @return the configured {@link SnsClient} instance
     */
    @Bean("snsClient")
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.US_EAST_2)
                .build();
    }
}

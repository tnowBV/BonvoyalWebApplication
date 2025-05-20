package com.bonvoyal.trip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

/**
 * Service responsible for publishing trip form submission messages to an AWS SNS topic.
 *
 * <p>This service is used to asynchronously notify other systems or services about new
 * trip form submissions by sending messages to a configured SNS topic. This can be useful
 * for decoupled architectures where downstream processing (e.g., analytics, itinerary
 * generation, or notifications) is handled by subscribers to the SNS topic.</p>
 *
 * <p>The SNS topic ARN is injected via the {@code aws.sns.topic.arn} property. Ensure the
 * AWS SDK is properly configured with credentials and region to allow publishing.</p>
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link SnsClient} – AWS SNS client for sending messages</li>
 *   <li>{@code aws.sns.topic.arn} – Spring property to specify the target SNS topic ARN</li>
 * </ul>
 *
 * <p>Logging is provided via SLF4J.</p>
 *
 * @author TnowBV
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublishTripFormToSnsService {

    @Value("${aws.sns.topic.arn}")
    private String topicArn;

    @Autowired
    private final SnsClient snsClient;

    /**
     * Publishes a message to the configured Amazon SNS topic.
     *
     * <p>This method builds a {@link PublishRequest} with the specified message and sends
     * it to the SNS topic. Logs the published message upon successful dispatch.</p>
     *
     * @param message the message to publish to the SNS topic, typically a serialized
     *                representation of the trip form data
     */
    public void publishMessage(String message) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build();

        snsClient.publish(request);
        log.info("Published message to SNS: {}", message);
    }
}

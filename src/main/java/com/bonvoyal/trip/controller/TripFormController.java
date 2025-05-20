package com.bonvoyal.trip.controller;

import com.bonvoyal.trip.dto.TripFormData;
import com.bonvoyal.trip.entities.Trip;
import com.bonvoyal.trip.service.PublishTripFormToSnsService;
import com.bonvoyal.trip.service.TripService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sns.model.SnsException;

/**
 * REST controller for handling trip form submissions.
 *
 * <p>This controller exposes an endpoint for clients to submit trip planning data, such as
 * destination, travel dates, and user hobbies. It accepts POST requests and returns a
 * confirmation message.</p>
 *
 * <p>{@code @CrossOrigin} is configured to allow requests from any origin during development.
 * This should be restricted in production environments.</p>
 *
 * @author tnowBV
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class TripFormController {

    @Autowired
    TripService tripService;

    @Autowired
    PublishTripFormToSnsService publishTripFormToSnsService;

    /**
     * Handles POST requests for trip form submission.
     *
     * <p>Accepts JSON data mapped to the {@link TripFormData} DTO and logs it to the console.
     * In a real-world application, this data could be saved to a database, processed, or used
     * to generate a custom itinerary.</p>
     *
     * @param formData the trip form data submitted by the user
     * @return a simple success message confirming receipt of the form
     */
    @PostMapping("/submit")
    public ResponseEntity<String> handleSubmit(@RequestBody final TripFormData formData) {
        if (tripService.validateForm(formData)) {
            final ObjectMapper mapper = new ObjectMapper();
            try {
                final Trip savedTrip = tripService.saveTrip(
                        tripService.convertDtoToEntity(formData));

                publishTripFormToSnsService.publishMessage(mapper.writeValueAsString(formData));

                return ResponseEntity.ok("Form received! Here is your trip ID: "
                        + savedTrip.getId());

            } catch (JsonProcessingException jsonProcessingException) {
                log.error(jsonProcessingException.getMessage());
                //TODO: replace with internal issues codes JIRA Story: PB-15
                return ResponseEntity.internalServerError().body("There was an internal issue");
            } catch (SnsException snsException) {
                log.error(snsException.getMessage());
                return ResponseEntity.internalServerError().body("There was an internal issue");
            }
        } else {
            return ResponseEntity.badRequest().body("Bad Request: Form validation failed");
        }
    }


}

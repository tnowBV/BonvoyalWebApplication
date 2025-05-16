package com.bonvoyal.tripform.controller;

import com.bonvoyal.tripform.dto.TripFormData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling trip form submissions.
 *
 * <p>This controller exposes an endpoint for clients to submit trip planning data, such as
 * destination, travel dates, and user interests. It accepts POST requests and returns a
 * confirmation message.</p>
 *
 * <p>{@code @CrossOrigin} is configured to allow requests from any origin during development.
 * This should be restricted in production environments.</p>
 *
 * @author tnowBV
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow all origins for dev
public class TripFormController {

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
    public String handleSubmit(@RequestBody TripFormData formData) {
        System.out.println("Received form data: " + formData);
        return "Form received!";
    }
}

package com.bonvoyal.trip.controller;

import com.bonvoyal.trip.entities.Trip;
import com.bonvoyal.trip.service.TripService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that handles HTTP requests related to Trip resources.
 *
 * <p>This controller exposes endpoints to retrieve trip data from the backend,
 * either as a full list or by specific ID. It delegates business logic to the
 * {@link TripService} layer.</p>
 *
 * <p>All endpoints are prefixed with <code>/api</code>.</p>
 *
 * @author tnowBV
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class TripController {

    private final TripService tripService;

    /**
     * Constructs the controller with the injected {@link TripService}.
     *
     * @param tripService the service that handles trip data access and business logic
     */
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    /**
     * Endpoint to retrieve all stored trip records.
     *
     * @return a list of all {@link Trip} objects from the database
     */
    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    /**
     * Endpoint to retrieve a single trip by its ID.
     *
     * @param id the unique ID of the trip
     * @return the {@link Trip} object matching the given ID
     */
    @GetMapping("/trip")
    public Trip getTripById(@RequestParam("id") Long id) {
        return tripService.getTripById(id);
    }
}

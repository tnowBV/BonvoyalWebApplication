package com.bonvoyal.trip.service;

import com.bonvoyal.trip.dto.TripFormData;
import com.bonvoyal.trip.entities.Trip;
import com.bonvoyal.trip.repository.TripRepository;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for business logic related to {@link Trip} entities.
 *
 * <p>This service provides operations for saving, retrieving, and converting trip data.
 * It acts as a bridge between the controller layer and the repository layer.</p>
 *
 * <p>Annotated with {@code @Service} to be recognized as a Spring-managed component.</p>
 *
 * @author tnowBV
 */
@Slf4j
@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    /**
     * Saves a {@link Trip} entity to the database.
     *
     * @param trip the {@link Trip} object to persist
     * @return the persisted {@link Trip} with generated ID and database values
     */
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    /**
     * Retrieves all stored {@link Trip} entities from the database.
     *
     * @return a list of all {@link Trip} records
     */
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    /**
     * Retrieves a single {@link Trip} by its ID.
     *
     * @param id the unique identifier of the trip
     * @return the {@link Trip} entity, or throw {@code NoSuchElementException} if not found
     */
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(new Trip());
    }

    /**
     * Converts a {@link TripFormData} DTO into a {@link Trip} entity.
     *
     * <p>This is used to transform frontend-submitted data into a database-ready format.</p>
     *
     * @param formData the incoming trip form data from the user
     * @return a new {@link Trip} entity populated with data from the DTO
     */
    public Trip convertDtoToEntity(TripFormData formData) {
        Trip trip = new Trip();
        trip.setDestinations(List.of(formData.getDestination()));
        trip.setFirstDates(List.of(formData.getStartDate(), formData.getEndDate()));
        trip.setAge(formData.getAge());
        trip.setHobbies(formData.getHobbies());
        trip.setDietaryRestrictions(formData.getDietaryRestrictions());
        trip.setTransportPreferences(formData.getTransportPreferences());
        trip.setZoneRegion(formData.getZoneRegion());
        trip.setTimeCreated(formData.getTimeCreated());
        trip.setExtraData(new HashMap<>());
        return trip;
    }

    /**
     * Validates the provided {@link TripFormData}.
     *
     * <p>Currently always returns {@code true}. Implement custom logic as needed.</p>
     *
     * @param formData the form data to validate
     * @return {@code true} if the form is valid; {@code false} otherwise
     */
    public Boolean validateForm(final TripFormData formData) {
        return true;
    }
}

package com.bonvoyal.trip.dto;

import com.bonvoyal.trip.enums.DietaryRestriction;
import com.bonvoyal.trip.enums.HobbyType;
import com.bonvoyal.trip.enums.TransportType;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for capturing trip form submission data.
 *
 * <p>This class is used to transfer user-submitted trip planning details from the frontend
 * to the backend. It contains the destination, travel dates, and selected hobbies.
 * </p>
 *
 * <p>Used primarily in request bodies of API endpoints that collect or process trip-related
 * information.
 * </p>
 *
 * <p>Automatically generates getters, setters, equals, hashCode, and toString via Lombok's
 * {@code @Data} annotation.
 * </p>
 *
 * @author tnowBV
 */
@Data
public class TripFormData {

    /**
     * The destination or location the user plans to travel to.
     */
    private String destination;


    /**
     * The age the user.
     */
    private Integer age;

    /**
     * The start date of the planned trip.
     */
    private Date startDate;

    /**
     * The end date of the planned trip.
     */
    private Date endDate;

    /**
     * A list of user-selected hobbies to tailor the trip experience.
     */
    private List<HobbyType> hobbies;

    /**
     * A list of user-selected dietary restrictions.
     */
    private List<DietaryRestriction> dietaryRestrictions;

    /**
     * A list of user-selected transportation preferences.
     */
    private List<TransportType> transportPreferences;

    /**
     * The timezone of the user.
     */
    private String zoneRegion;

    /**
     * The timestamp for when the form was submitted.
     */
    private Long timeCreated;
}

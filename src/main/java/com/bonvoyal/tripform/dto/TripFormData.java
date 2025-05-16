package com.bonvoyal.tripform.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for capturing trip form submission data.
 *
 * <p>This class is used to transfer user-submitted trip planning details from the frontend
 * to the backend. It contains the destination, travel dates, and selected interests.</p>
 *
 * <p>Used primarily in request bodies of API endpoints that collect or process trip-related
 * information.</p>
 *
 * <p>Automatically generates getters, setters, equals, hashCode, and toString via Lombok's
 * {@code @Data} annotation.</p>
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
     * The start date of the planned trip.
     */
    private Date startDate;

    /**
     * The end date of the planned trip.
     */
    private Date endDate;

    /**
     * A list of user-selected interests to tailor the trip experience.
     */
    private List<String> interests;
}

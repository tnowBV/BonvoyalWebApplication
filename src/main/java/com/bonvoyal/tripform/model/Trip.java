package com.bonvoyal.tripform.model;

import com.bonvoyal.tripform.enums.DietaryRestriction;
import com.bonvoyal.tripform.enums.TransportType;
import com.bonvoyal.util.JsonConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import lombok.Data;

/**
 * Entity representing a user's travel preferences and options for planning a trip.
 *
 * <p>This class is persisted in the database and stores multiple sets of date ranges,
 * user preferences such as destinations, interests, transport types, and dietary restrictions,
 * along with any additional user-submitted metadata.
 * </p>
 *
 * <p>Uses JPA annotations for ORM mapping and Lombok for boilerplate code reduction.
 * </p>
 *
 * <p>The {@code extraData} field allows for dynamic schema extension by storing arbitrary
 * key-value pairs in JSON format.
 * </p>
 *
 * @author tnowBV
 */
@Entity
@Data
public class Trip {

    /**
     * Unique identifier for the trip record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user's age. Optional demographic data for trip tailoring.
     */
    private Integer age;

    /**
     * A list of one or more desired destinations.
     */
    @ElementCollection
    private List<String> destination;

    /**
     * User's first preferred date range for travel.
     */
    @Nullable
    @Temporal(TemporalType.DATE)
    private Pair<Date, Date> firstDates;

    /**
     * User's second preferred date range for travel.
     */
    @Nullable
    @Temporal(TemporalType.DATE)
    private Pair<Date, Date> secondDates;

    /**
     * User's third preferred date range for travel.
     */
    @Nullable
    @Temporal(TemporalType.DATE)
    private Pair<Date, Date> thirdDates;

    /**
     * User's fourth preferred date range for travel.
     */
    @Nullable
    @Temporal(TemporalType.DATE)
    private Pair<Date, Date> fourthDates;

    /**
     * User's fifth preferred date range for travel.
     */
    @Nullable
    @Temporal(TemporalType.DATE)
    private Pair<Date, Date> fifthDates;

    /**
     * List of the user's interests for personalization purposes.
     */
    @ElementCollection
    private List<String> interests;

    /**
     * List of preferred transportation types.
     */
    @ElementCollection
    private List<TransportType> transportPreferences;

    /**
     * List of dietary restrictions the user has.
     */
    @ElementCollection
    private List<DietaryRestriction> dietaryRestrictions;

    /**
     * Arbitrary extra data stored in JSON format.
     * Useful for flexible schema evolution and storing additional form inputs.
     */
    @Column(name = "extra_data", columnDefinition = "jsonb")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> extraData;
}
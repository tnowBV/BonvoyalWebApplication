package com.bonvoyal.trip.entities;

import com.bonvoyal.trip.enums.DietaryRestriction;
import com.bonvoyal.trip.enums.HobbyType;
import com.bonvoyal.trip.enums.TransportType;
import com.bonvoyal.util.JsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

/**
 * Entity representing a user's travel preferences and options for planning a trip.
 *
 * <p>This class is persisted in the database and stores multiple sets of date ranges,
 * user preferences such as destinations, hobbies, transport types, and dietary restrictions,
 * along with any additional user-submitted metadata.
 * </p>
 *
 * <p>Uses JPA annotations for ORM mapping and Lombok for boilerplate code reduction.
 * </p>
 *
 * <p>The {@code extraData} field allows for dynamic schema extension by storing arbitrary
 * key-value Lists in JSON format.
 * </p>
 *
 * @author tnowBV
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "age", columnDefinition = "INTEGER")
    private Integer age;

    /**
     * A list of one or more desired destinations.
     */
    @Column(name = "destinations", columnDefinition = "TEXT")
    private List<String> destinations;

    /**
     * User's first preferred date range for travel.
     */
    @Column(name = "first_dates", columnDefinition = "DATE[]")
    private List<Date> firstDates;

    /**
     * User's second preferred date range for travel.
     */
    @Column(name = "second_dates", columnDefinition = "DATE[]")
    private List<Date> secondDates;

    /**
     * User's third preferred date range for travel.
     */
    @Column(name = "third_dates", columnDefinition = "DATE[]")
    private List<Date> thirdDates;

    /**
     * User's fourth preferred date range for travel.
     */
    @Column(name = "fourth_dates", columnDefinition = "DATE[]")
    private List<Date> fourthDates;

    /**
     * User's fifth preferred date range for travel.
     */
    @Column(name = "fifth_dates", columnDefinition = "DATE[]")
    private List<Date> fifthDates;

    /**
     * List of the user's hobbies for personalization purposes.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "hobbies", columnDefinition = "TEXT[]")
    private List<HobbyType> hobbies;

    /**
     * List of dietary restrictions the user has.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "dietary_restrictions", columnDefinition = "TEXT[]")
    private List<DietaryRestriction> dietaryRestrictions;

    /**
     * List of preferred transportation types.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "transport_preferences", columnDefinition = "TEXT[]")
    private List<TransportType> transportPreferences;



    /**
     * Arbitrary extra data stored in JSON format.
     * Useful for flexible schema evolution and storing additional form inputs.
     */
    @Column(name = "extra_data", columnDefinition = "JSONB")
    @Convert(converter = JsonConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private Map<String, Object> extraData;
}
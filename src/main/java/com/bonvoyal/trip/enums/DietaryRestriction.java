package com.bonvoyal.trip.enums;

/**
 * Enum representing common dietary restrictions that may influence
 * travel plans or accommodations.
 *
 * <p>This is used to tailor travel recommendations, restaurant suggestions,
 * or hotel amenities according to the user’s dietary needs.
 * </p>
 *
 * <p>Can be extended to support additional restrictions as needed.
 * </p>
 *
 * @author tnowBV
 */
public enum DietaryRestriction {
    NONE,
    VEGETARIAN,
    VEGAN,
    GLUTEN_FREE,
    DAIRY_FREE,
    HALAL,
    KOSHER,
    NUT_ALLERGY,
    SHELLFISH_ALLERGY
}
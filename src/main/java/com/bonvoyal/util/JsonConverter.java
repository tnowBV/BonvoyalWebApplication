package com.bonvoyal.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JPA AttributeConverter to serialize and deserialize a {@link Map} of
 * {@code String -> Object} into a JSON {@link String} for database storage.
 *
 * <p>This converter enables storing flexible or dynamic JSON data using a single
 * column (e.g., PostgreSQL's {@code jsonb} type) by automatically converting
 * Java Maps to JSON strings and vice versa.
 * </p>
 *
 * <p>The conversion uses Jackson's {@link ObjectMapper} to handle the mapping
 * between the JSON string and the Java Map.
 * </p>
 *
 * <p>This is useful for capturing unstructured, optional, or extensible
 * metadata such as user preferences, optional flags, or feature toggles.
 * </p>
 *
 * @author tnowBV
 */
@Converter
public class JsonConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts a {@link Map} to a JSON string for database storage.
     *
     * @param attribute the map to convert
     * @return JSON string representation of the map or {@code null} if attribute is null
     * @throws IllegalArgumentException if conversion to JSON fails
     */
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to convert map to JSON string", e);
        }
    }

    /**
     * Converts a JSON string from the database into a {@link Map}.
     *
     * @param dbData the JSON string stored in the database
     * @return a {@link Map} representation or an empty map if input is null
     * @throws IllegalArgumentException if conversion from JSON fails
     */
    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return new HashMap<>();
        }
        try {
            return mapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to convert JSON string to map", e);
        }
    }
}

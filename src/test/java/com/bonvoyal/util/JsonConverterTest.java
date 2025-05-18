package com.bonvoyal.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class JsonConverterTest {

    private JsonConverter converter;

    @BeforeEach
    void setUp() {
        converter = new JsonConverter();
    }

    @Test
    void testConvertToDatabaseColumn_withValidMap() {
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Alice");
        input.put("age", 30);

        String json = converter.convertToDatabaseColumn(input);

        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"Alice\""));
        assertTrue(json.contains("\"age\":30"));
    }

    @Test
    void testConvertToDatabaseColumn_withNullMap() {
        String result = converter.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void testConvertToEntityAttribute_withValidJson() {
        String json = "{\"city\":\"Paris\",\"nights\":5}";

        Map<String, Object> result = converter.convertToEntityAttribute(json);

        assertNotNull(result);
        assertEquals("Paris", result.get("city"));
        assertEquals(5, result.get("nights"));
    }

    @Test
    void testConvertToEntityAttribute_withNull() {
        Map<String, Object> result = converter.convertToEntityAttribute(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertToEntityAttribute_withInvalidJson_shouldThrow() {
        String invalidJson = "{invalid-json}";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                converter.convertToEntityAttribute(invalidJson)
        );
        assertTrue(ex.getMessage().contains("Failed to convert JSON string to map"));
    }

    @Test
    void testConvertToDatabaseColumn_withNonSerializableObject_shouldThrow() {
        Map<String, Object> input = new HashMap<>();
        input.put("nonSerializable", new Object()); // Object is not JSON serializable by default

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                converter.convertToDatabaseColumn(input)
        );
        assertTrue(ex.getMessage().contains("Failed to convert map to JSON string"));
    }
}

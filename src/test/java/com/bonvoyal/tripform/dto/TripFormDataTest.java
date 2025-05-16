package com.bonvoyal.tripform.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

class TripFormDataTest {

    @Test
    void testFieldAccessors() {
        TripFormData data = new TripFormData();
        Date start = new Date();
        Date end = new Date();
        List<String> interests = Arrays.asList("beaches", "food");

        data.setDestination("Barcelona");
        data.setStartDate(start);
        data.setEndDate(end);
        data.setInterests(interests);

        assertEquals("Barcelona", data.getDestination());
        assertEquals(start, data.getStartDate());
        assertEquals(end, data.getEndDate());
        assertEquals(interests, data.getInterests());
    }

    @Test
    void testEqualsAndHashCode() {
        Date start = new Date();
        Date end = new Date();
        List<String> interests = Arrays.asList("history", "jazz");

        TripFormData data1 = new TripFormData();
        data1.setDestination("Paris");
        data1.setStartDate(start);
        data1.setEndDate(end);
        data1.setInterests(interests);

        TripFormData data2 = new TripFormData();
        data2.setDestination("Paris");
        data2.setStartDate(start);
        data2.setEndDate(end);
        data2.setInterests(interests);

        assertEquals(data1, data2);
        assertEquals(data1.hashCode(), data2.hashCode());
    }

    @Test
    void testToString() {
        TripFormData data = new TripFormData();
        data.setDestination("Tokyo");
        data.setStartDate(new Date());
        data.setEndDate(new Date());
        data.setInterests(Arrays.asList("food", "culture"));

        String str = data.toString();
        assertTrue(str.contains("Tokyo"));
        assertTrue(str.contains("food"));
    }
}

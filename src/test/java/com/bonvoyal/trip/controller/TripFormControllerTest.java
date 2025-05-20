package com.bonvoyal.trip.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bonvoyal.trip.dto.TripFormData;
import com.bonvoyal.trip.entities.Trip;
import com.bonvoyal.trip.enums.HobbyType;
import com.bonvoyal.trip.service.PublishTripFormToSnsService;
import com.bonvoyal.trip.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit tests for {@link TripFormController}.
 */
class TripFormControllerTest {

    private MockMvc mockMvc;
    private TripService tripService;
    private PublishTripFormToSnsService publishTripFormToSnsService;
    private ObjectMapper objectMapper;

    /**
     * Sets up the test dependencies and controller.
     */
    @BeforeEach
    void setup() {
        tripService = Mockito.mock(TripService.class);
        publishTripFormToSnsService = Mockito.mock(PublishTripFormToSnsService.class);

        TripFormController controller = new TripFormController();
        controller.tripService = tripService;
        controller.publishTripFormToSnsService = publishTripFormToSnsService;

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testHandleSubmit_successfulSubmission_returnsOk() throws Exception {
        TripFormData formData = new TripFormData();
        formData.setDestination("Tokyo");
        formData.setStartDate(new Date());
        formData.setEndDate(new Date());
        formData.setAge(30);
        formData.setHobbies(List.of(HobbyType.FOOD, HobbyType.JAZZ));
        formData.setDietaryRestrictions(List.of());
        formData.setTransportPreferences(List.of());

        Trip convertedTrip = new Trip();
        convertedTrip.setId(1L);

        when(tripService.validateForm(formData)).thenReturn(true);
        when(tripService.convertDtoToEntity(formData)).thenReturn(convertedTrip);
        when(tripService.saveTrip(convertedTrip)).thenReturn(convertedTrip);

        String json = objectMapper.writeValueAsString(formData);

        mockMvc.perform(post("/api/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Form received! Here is your trip ID: 1"));

        verify(publishTripFormToSnsService).publishMessage(json);
    }

    @Test
    void testHandleSubmit_validationFails_returnsBadRequest() throws Exception {
        TripFormData formData = new TripFormData();
        formData.setDestination("Nowhere");

        when(tripService.validateForm(formData)).thenReturn(false);

        String json = objectMapper.writeValueAsString(formData);

        mockMvc.perform(post("/api/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request: Form validation failed"));
    }
}

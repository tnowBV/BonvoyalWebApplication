package com.bonvoyal.tripform.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bonvoyal.tripform.dto.TripFormData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class TripFormControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        TripFormController controller = new TripFormController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testHandleSubmit() throws Exception {
        TripFormData formData = new TripFormData();
        formData.setDestination("Barcelona");
        formData.setStartDate(new Date());
        formData.setEndDate(new Date());
        formData.setInterests(Arrays.asList("beaches", "food", "history"));

        String jsonPayload = objectMapper.writeValueAsString(formData);

        mockMvc.perform(post("/api/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(content().string("Form received!"));
    }
}

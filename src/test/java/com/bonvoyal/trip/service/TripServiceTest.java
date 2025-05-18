package com.bonvoyal.trip.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bonvoyal.trip.dto.TripFormData;
import com.bonvoyal.trip.entities.Trip;
import com.bonvoyal.trip.enums.DietaryRestriction;
import com.bonvoyal.trip.enums.HobbyType;
import com.bonvoyal.trip.enums.TransportType;
import com.bonvoyal.trip.repository.TripRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes @Mock and @InjectMocks
    }

    @Test
    void testSaveTrip() {
        Trip trip = new Trip();
        trip.setId(1L);

        when(tripRepository.save(trip)).thenReturn(trip);

        Trip savedTrip = tripService.saveTrip(trip);
        assertEquals(1L, savedTrip.getId());
        verify(tripRepository, times(1)).save(trip);
    }

    @Test
    void testGetAllTrips() {
        List<Trip> mockTrips = List.of(new Trip(), new Trip());
        when(tripRepository.findAll()).thenReturn(mockTrips);

        List<Trip> result = tripService.getAllTrips();
        assertEquals(2, result.size());
        verify(tripRepository).findAll();
    }

    @Test
    void testGetTripById_found() {
        Trip trip = new Trip();
        trip.setId(42L);
        when(tripRepository.findById(42L)).thenReturn(Optional.of(trip));

        Trip result = tripService.getTripById(42L);
        assertEquals(42L, result.getId());
    }

    @Test
    void testGetTripById_notFound() {
        when(tripRepository.findById(999L)).thenReturn(Optional.empty());

        Trip result = tripService.getTripById(999L);
        assertNotNull(result); // Should return empty Trip object
    }

    @Test
    void testConvertDtoToEntity() {
        TripFormData dto = new TripFormData();
        dto.setDestination("Tokyo");
        dto.setAge(25);
        dto.setStartDate(new Date());
        dto.setEndDate(new Date());
        dto.setHobbies(List.of(HobbyType.FOOD));
        dto.setDietaryRestrictions(List.of(DietaryRestriction.GLUTEN_FREE));
        dto.setTransportPreferences(List.of(TransportType.PUBLIC_TRANSIT));

        Trip result = tripService.convertDtoToEntity(dto);

        assertEquals("Tokyo", result.getDestinations().get(0));
        assertEquals(25, result.getAge());
        assertEquals(List.of(HobbyType.FOOD), result.getHobbies());
        assertEquals(List.of(DietaryRestriction.GLUTEN_FREE), result.getDietaryRestrictions());
        assertEquals(List.of(TransportType.PUBLIC_TRANSIT), result.getTransportPreferences());
        assertNotNull(result.getFirstDates());
    }

    @Test
    void testValidateFormAlwaysReturnsTrue() {
        assertTrue(tripService.validateForm(new TripFormData()));
    }
}

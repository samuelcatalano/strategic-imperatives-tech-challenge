package co.uk.imperatives.exercise.entity;

import co.uk.imperatives.exercise.repository.LocationRepository;
import co.uk.imperatives.exercise.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.openMocks;

public class LocationTest {

    private LocationService service;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private ObjectMapper objectMapper;

    private static final String LOCATION_CITY = "City-Test";
    private static final String LOCATION_BOROUGH = "Borough-Test";
    private static final Long ID = 1L;

    @BeforeEach
    void setup() {
        openMocks(this);
        service = new LocationService(locationRepository, objectMapper);
    }

    @Nested
    @DisplayName("Location")
    class LocationUniTest {

        @Test
        @DisplayName("Should returns a successful response")
        void location_returnsSuccessfulResponse() throws Exception {
            // Given
            final Location request = getCompleteLocation();

            doReturn(Optional.of(Location.builder().city(LOCATION_CITY).borough(LOCATION_BOROUGH).id(ID).build()))
                    .when(locationRepository).findById(ID);

            // When
            final Location response = service.findById(request.getId());

            // Then
            assertThat(response.getId(), is(ID));
            assertThat(response.getCity(), is(LOCATION_CITY));
            assertThat(response.getBorough(), is(LOCATION_BOROUGH));
        }

        @Test
        @DisplayName("Should returns error response")
        void location_returnsErrorResponse() throws Exception {
            // Given
            final Location request = getCompleteLocation();

            // When
            doThrow(new EntityNotFoundException(String.format("No entity found with id %s", ID)))
                    .when(locationRepository).findById(ID);

            final Exception exception = assertThrows(EntityNotFoundException.class,
                    () -> locationRepository.findById(request.getId()),
                    "Should throw IdScanException with missing or invalid request details");

            // Then
            assertThat(exception.getMessage(), is(String.format("No entity found with id %s", ID)));
        }
    }

    private Location getCompleteLocation() {
        return Location.builder().city(LOCATION_CITY).borough(LOCATION_BOROUGH).id(ID).build();
    }
}
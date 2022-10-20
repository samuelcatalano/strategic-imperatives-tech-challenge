package co.uk.imperatives.exercise.entity;

import co.uk.imperatives.exercise.repository.ProviderRepository;
import co.uk.imperatives.exercise.service.ProviderService;
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

public class ProviderTest {

    private ProviderService service;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private ObjectMapper objectMapper;

    private static final String PROVIDER_NAME = "Provider-Test";
    private static final Long ID = 1L;

    @BeforeEach
    void setup() {
        openMocks(this);
        service = new ProviderService(providerRepository, objectMapper);
    }

    @Nested
    @DisplayName("Provider")
    class ProviderUniTest {

        @Test
        @DisplayName("Should returns a successful response")
        void provider_returnsSuccessfulResponse() throws Exception {
            // Given
            final Provider request = getCompleteProvider();

            doReturn(Optional.of(Provider.builder().name(PROVIDER_NAME).id(ID).build()))
                    .when(providerRepository).findById(ID);

            // When
            final Provider response = service.findById(request.getId());

            // Then
            assertThat(response.getId(), is(ID));
            assertThat(response.getName(), is(PROVIDER_NAME));
        }

        @Test
        @DisplayName("Should returns error response")
        void provider_returnsErrorResponse() throws Exception {
            // Given
            final Provider request = getCompleteProvider();

            // When
            doThrow(new EntityNotFoundException(String.format("No entity found with id %s", ID)))
                    .when(providerRepository).findById(ID);

            final Exception exception = assertThrows(EntityNotFoundException.class,
                    () -> providerRepository.findById(request.getId()),
                    "Should throw IdScanException with missing or invalid request details");

            // Then
            assertThat(exception.getMessage(), is(String.format("No entity found with id %s", ID)));
        }
    }

    private Provider getCompleteProvider() {
        return Provider.builder().name(PROVIDER_NAME).id(ID).build();
    }
}
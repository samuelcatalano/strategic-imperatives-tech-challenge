package co.uk.imperatives.exercise.entity;

import co.uk.imperatives.exercise.repository.BillingRepository;
import co.uk.imperatives.exercise.service.BillingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.openMocks;

public class BillingTest {

    private BillingService service;

    @Mock
    private BillingRepository billingRepository;

    @Mock
    private ObjectMapper objectMapper;

    private static final String LOCATION_CITY = "City-Test";
    private static final String LOCATION_BOROUGH = "Borough-Test";
    private static final String PROVIDER_NAME = "Provider-Test";
    private static final Long ID = 1L;

    @BeforeEach
    void setup() {
        openMocks(this);
        service = new BillingService(billingRepository, objectMapper);
    }

    @Nested
    @DisplayName("Billing")
    class BillingUniTest {

        @Test
        @DisplayName("Should returns a successful response")
        void billing_returnsSuccessfulResponse() throws Exception {
            // Given
            final Billing request = getCompleteBilling();

            doReturn(Optional.of(Billing.builder().id(ID).amount(BigDecimal.TEN).provider(getCompleteProvider())
                    .location(getCompleteLocation()).build())).when(billingRepository).findById(ID);

            // When
            final Billing response = service.findById(request.getId());

            // Then
            assertThat(response.getId(), is(ID));
            assertThat(response.getAmount(), is(BigDecimal.TEN));
            assertThat(response.getProvider().getId(), is(ID));
            assertThat(response.getLocation().getCity(), is(LOCATION_CITY));
            assertThat(response.getLocation().getBorough(), is(LOCATION_BOROUGH));
            assertThat(response.getProvider().getId(), is(ID));
            assertThat(response.getProvider().getName(), is(PROVIDER_NAME));
        }

        @Test
        @DisplayName("Should returns error response")
        void billing_returnsErrorResponse() throws Exception {
            // Given
            final Billing request = getCompleteBilling();

            // When
            doThrow(new EntityNotFoundException(String.format("No entity found with id %s", ID)))
                    .when(billingRepository).findById(ID);

            final Exception exception = assertThrows(EntityNotFoundException.class,
                    () -> billingRepository.findById(request.getId()),
                    "Should throw IdScanException with missing or invalid request details");

            // Then
            assertThat(exception.getMessage(), is(String.format("No entity found with id %s", ID)));
        }
    }

    private Billing getCompleteBilling() {
        return Billing.builder().id(ID).provider(getCompleteProvider()).location(getCompleteLocation())
                .amount(BigDecimal.TEN).build();
    }

    private Provider getCompleteProvider() {
        return Provider.builder().name(PROVIDER_NAME).id(ID).build();
    }

    private Location getCompleteLocation() {
        return Location.builder().city(LOCATION_CITY).borough(LOCATION_BOROUGH).id(ID).build();
    }
}

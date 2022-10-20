package co.uk.imperatives.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingDTO {

    private Long id;
    private ProviderDTO provider;
    private LocationDTO location;
    private BigDecimal amount;
}

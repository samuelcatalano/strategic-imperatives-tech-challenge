package co.uk.imperatives.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO implements Serializable {

    private Long id;
    private String city;
    private String borough;
}

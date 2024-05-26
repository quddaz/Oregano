package Oregano.Backend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NumberOfRegisteredDisabledPeople {
    @JsonProperty("C1_NM")
    private String C1_NM; // region
    @JsonProperty("DT")
    private String DT; //number
}

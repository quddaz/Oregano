package Oregano.Backend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EstimatedSixMonthsIncome {

    @JsonProperty("PRD_DE")
    private String PRD_DE; // 분기

    @JsonProperty("C1_NM")
    private String C1_NM;
    @JsonProperty("ITM_NM")
    private String ITM_NM;

    @JsonProperty("DT")
    private String DT;

    @JsonProperty("UNIT_NM")
    private String UNIT_NM;

}

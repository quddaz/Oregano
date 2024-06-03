package Oregano.Backend.ApiService;

import Oregano.Backend.DTO.NumberOfRegisteredDisabledPeople;
import Oregano.Backend.Exception.ApiException;
import Oregano.Backend.Exception.Enum.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DecimalFormat;
import java.util.List;


@Service
public class NumberOfRegisteredDisabledPeopleService {
  private final WebClient webClient;
  private final String url;

  @Autowired
  public NumberOfRegisteredDisabledPeopleService(WebClient.Builder webClientBuilder, @Value("${numberOfRegisteredDisabledPeople.url}") String url) {
    this.webClient = webClientBuilder.build();
    this.url = url;
  }

    public List<NumberOfRegisteredDisabledPeople> getFilteredNumberOfRegisteredDisabledPeople() {
        // WebClient를 사용하여 OpenAPI에 요청
        String response = this.webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        try {
            // ObjectMapper를 사용하여 JSON 문자열을 객체로 변환하여 리스트로 반환
            ObjectMapper objectMapper = new ObjectMapper();
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, NumberOfRegisteredDisabledPeople.class);
            List<NumberOfRegisteredDisabledPeople> list = objectMapper.readValue(response, type);


            return list;
        } catch (JsonProcessingException e) {
            System.err.println("JSON parsing error: " + e.getMessage());
            throw new ApiException(ErrorCode.JSON_PARSING_ERROR);
        } catch (Exception ex) {
            System.err.println("Error response from WebClient: " + ex.getMessage());
            throw new ApiException(ErrorCode.API_CALL_ERROR);
        }
    }

}

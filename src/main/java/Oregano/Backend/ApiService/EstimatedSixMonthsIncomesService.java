package Oregano.Backend.ApiService;

import Oregano.Backend.DTO.EstimatedSixMonthsIncome;
import Oregano.Backend.Exception.ApiException;
import Oregano.Backend.Exception.Enum.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class EstimatedSixMonthsIncomesService {

    private final WebClient webClient;

    private final String url;

    @Autowired
    public EstimatedSixMonthsIncomesService(WebClient.Builder webClientBuilder, @Value("${estimatedSixMonthsIncomes.url}") String url) {
        this.webClient = webClientBuilder.build();
        this.url = url;
    }

    public Map<String, List<EstimatedSixMonthsIncome>> getFilteredEstimatedSixMonthsIncome() {
        try {
            // WebClient를 사용하여 OpenAPI에 요청
            String response = this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            // ObjectMapper를 사용하여 JSON 문자열을 객체로 변환하여 리스트로 반환
            ObjectMapper objectMapper = new ObjectMapper();
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, EstimatedSixMonthsIncome.class);
            List<EstimatedSixMonthsIncome> list = objectMapper.readValue(response, type);

            // ITM_NM에 따라 그룹화
            return list.stream()
                .collect(Collectors.groupingBy(EstimatedSixMonthsIncome::getITM_NM));
        } catch (JsonProcessingException e) {
            log.error("JSON parsing error: " + e.getMessage());
            throw new ApiException(ErrorCode.JSON_PARSING_ERROR);
        } catch (WebClientRequestException e) {
            log.error("Error response from WebClient: " + e.getMessage());
            throw new ApiException(ErrorCode.WEBCLIENT_CALL_ERROR);
        } catch (Exception e){
            log.error("Exception" + e.getMessage());
            throw new ApiException(ErrorCode.API_CALL_ERROR);
        }
    }

}

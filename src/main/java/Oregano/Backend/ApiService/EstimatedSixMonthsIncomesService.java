package Oregano.Backend.ApiService;

import Oregano.Backend.DTO.EstimatedSixMonthsIncome;
import Oregano.Backend.Exception.ApiException;
import Oregano.Backend.Exception.Enum.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
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
            Map<String, List<EstimatedSixMonthsIncome>> resultMap = new HashMap<>();
            for (EstimatedSixMonthsIncome income : list) {
                String itmNm = income.getITM_NM();
                resultMap.computeIfAbsent(itmNm, k -> new ArrayList<>()).add(income);
            }

            return resultMap;
        } catch (JsonProcessingException e) {
            throw new ApiException(ErrorCode.JSON_PARSING_ERROR);
        } catch (Exception ex) {
            throw new ApiException(ErrorCode.API_CALL_ERROR);
        }
    }

}

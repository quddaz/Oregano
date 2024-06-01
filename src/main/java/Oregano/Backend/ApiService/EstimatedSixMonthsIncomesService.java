package Oregano.Backend.ApiService;

import Oregano.Backend.DTO.EstimatedSixMonthsIncome;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstimatedSixMonthsIncomesService {

    private final WebClient webClient;
    private final String apiKey;

    @Autowired
    public EstimatedSixMonthsIncomesService(WebClient.Builder webClientBuilder, @Value("${kosis.api.key}") String apiKey) {
        this.webClient = webClientBuilder.build();
        this.apiKey = apiKey;
    }

    public Mono<Map<String,List<EstimatedSixMonthsIncome>>> getFilteredEstimatedSixMonthsIncome() {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + apiKey + "&itmId=num+wage+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=H&newEstPrdCnt=2&orgId=383&tblId=DT_38304_2013_N014";

        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .flatMapMany(response -> {
                    try {
                        // ObjectMapper를 사용하여 JSON 문자열을 객체로 변환
                        ObjectMapper objectMapper = new ObjectMapper();
                        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, EstimatedSixMonthsIncome.class);
                        List<EstimatedSixMonthsIncome> list = objectMapper.readValue(response, type);
                        return Flux.fromIterable(list);
                    } catch (JsonProcessingException e) {
                        System.err.println("JSON parsing error: " + e.getMessage());
                        return Flux.empty();
                    }
                })
                .collect(Collectors.groupingBy(EstimatedSixMonthsIncome::getITM_NM))
                .onErrorResume(WebClientResponseException.class, ex -> {
                    System.err.println("Error response from WebClient: " + ex.getMessage());
                    return Mono.empty();
                });
    }
}

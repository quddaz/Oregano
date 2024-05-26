package Oregano.Backend.Service;

import Oregano.Backend.DTO.NumberOfRegisteredDisabledPeople;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.List;


@Service
public class NumberOfRegisteredDisabledPeopleService {

    private final WebClient webClient;

    @Autowired
    public NumberOfRegisteredDisabledPeopleService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Flux<NumberOfRegisteredDisabledPeople> getFilteredNumberOfRegisteredDisabledPeople() {

        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=ZDIxNzk0ZjBkYzU4MjkxYjlkOTBlMGMyOTdkMmQ2MGI=&itmId=00+&objL1=ALL&objL2=CHUT0+&objL3=TT+&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&prdInterval=2023&orgId=117&tblId=DT_11761_N005";

        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .flatMapMany(response -> {
                    // 여기에 JSON을 NumberOfRegisteredDisabledPeople 객체로 변환하는 로직 추가
                    // 예: JSON 파싱 후 Flux<NumberOfRegisteredDisabledPeople>로 변환
                    try {
                        // ObjectMapper를 사용하여 JSON 문자열을 객체로 변환
                        ObjectMapper objectMapper = new ObjectMapper();
                        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, NumberOfRegisteredDisabledPeople.class);
                        List<NumberOfRegisteredDisabledPeople> list = objectMapper.readValue(response, type);
                        return Flux.fromIterable(list);
                    } catch (JsonProcessingException e) {
                        System.err.println("JSON parsing error: " + e.getMessage());
                        return Flux.empty();
                    }
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    System.err.println("Error response from WebClient: " + ex.getMessage());
                    return Flux.empty();
                });
    }

}

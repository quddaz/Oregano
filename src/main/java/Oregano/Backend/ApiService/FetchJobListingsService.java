package Oregano.Backend.ApiService;

import Oregano.Backend.DTO.FetchJobListingsDTO;
import Oregano.Backend.Service.JobListingsFilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchJobListingsService {

  private final WebClient webClient;
  private final JobListingsFilterService filterService;

  @Value("${fetchJob.key}")
  private String secretKey;

  public FetchJobListingsDTO.Items fetchJobListings(String region, String empType) {

    String url = "https://apis.data.go.kr/B552583/job/job_list_env?serviceKey=" + secretKey + "&pageNo=1&numOfRows=400";
    log.info("API 호출: region={}, empType={}", region, empType);

    try {
      FetchJobListingsDTO responseData = webClient.get()
          .uri(url)
          .retrieve()
          .bodyToMono(FetchJobListingsDTO.class)
          .block();
      if (responseData != null && responseData.getBody() != null) {
        return filterService.filterJobListings(responseData, region, empType).getBody().getItems();
      } else {
        log.error("데이터를 가져오는 데 실패했습니다.");
        throw new RuntimeException("데이터를 가져오는 데 실패했습니다.");
      }
    } catch (Exception e) {
      log.error("API 호출 중 예외 발생:", e);
      throw new RuntimeException("API 호출 중 예외 발생:", e);
    }
  }

}
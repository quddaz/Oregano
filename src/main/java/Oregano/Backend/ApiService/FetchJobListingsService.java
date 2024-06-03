package Oregano.Backend.ApiService;

import Oregano.Backend.DTO.FetchJobListingsDTO;
import Oregano.Backend.Exception.ApiException;
import Oregano.Backend.Exception.Enum.ErrorCode;
import Oregano.Backend.Service.JobListingsFilterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchJobListingsService {

  private final WebClient webClient;
  private final JobListingsFilterService filterService;

  @Value("${fetchJob.url}")
  private String url;

  public FetchJobListingsDTO fetchJobListings(String region, String empType) {
    log.info("API 호출: region={}, empType={}", region, empType);

    try {
      // WebClient를 사용하여 OpenAPI에 요청
      FetchJobListingsDTO responseData = webClient.get()
          .uri(url)
          .retrieve()
          .bodyToMono(FetchJobListingsDTO.class)
          .block();

      // 지역, 채용 타입으로 필터링
      return Optional.ofNullable(responseData)
          .map(data -> filterService.filterJobListings(data, region, empType))
          .orElseThrow(() -> new ApiException(ErrorCode.NO_DATA_FAIL_ERROR));
    } catch (WebClientRequestException e) {
      log.error("Error response from WebClient: " + e.getMessage());
      throw new ApiException(ErrorCode.WEBCLIENT_CALL_ERROR);
    } catch (Exception e){
      log.error("Exception" + e.getMessage());
      throw new ApiException(ErrorCode.API_CALL_ERROR);
    }
  }

}
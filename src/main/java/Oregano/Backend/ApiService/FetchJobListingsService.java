package Oregano.Backend.ApiService;

import Oregano.Backend.DTO.FetchJobListingsDTO;
import Oregano.Backend.Exception.ApiException;
import Oregano.Backend.Exception.Enum.ErrorCode;
import Oregano.Backend.Service.JobListingsFilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchJobListingsService {

  private final WebClient webClient;
  private final JobListingsFilterService filterService;

  @Value("${fetchJob.url}")
  private String url;

  public FetchJobListingsDTO.Items fetchJobListings(String region, String empType) {
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
        throw new ApiException(ErrorCode.NO_DATA_FAIL_ERROR);
      }
    } catch (Exception e) {
      throw new ApiException(ErrorCode.API_CALL_ERROR);
    }
  }

}
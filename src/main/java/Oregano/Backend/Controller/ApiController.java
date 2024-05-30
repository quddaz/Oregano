package Oregano.Backend.Controller;

import Oregano.Backend.DTO.FetchJobListingsDTO;
import Oregano.Backend.DTO.NumberOfRegisteredDisabledPeople;
import Oregano.Backend.ApiService.FetchJobListingsService;
import Oregano.Backend.ApiService.NumberOfRegisteredDisabledPeopleService;
import Oregano.Backend.DTO.ResponseDto;
import Oregano.Backend.Service.JobListingsFilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final NumberOfRegisteredDisabledPeopleService numberOfRegisteredDisabledPeopleService;
    private final FetchJobListingsService fetchJobListingsService;

    @GetMapping("/filtered-disabled-people")
    public Flux<NumberOfRegisteredDisabledPeople> getFilteredNumberOfRegisteredDisabledPeople() {
        return numberOfRegisteredDisabledPeopleService.getFilteredNumberOfRegisteredDisabledPeople();
    }
    @GetMapping("/fetchJobListings")
    public ResponseEntity<FetchJobListingsDTO.Items> callApi(
        @RequestParam(value = "region") String region
        ,@RequestParam(value = "empType") String empType
    ) {
        return new ResponseEntity<>(fetchJobListingsService.fetchJobListings(region, empType), HttpStatus.OK);
    }
    @GetMapping("/test")
    public Mono<String> get(){
        return fetchJobListingsService.test();
    }
}
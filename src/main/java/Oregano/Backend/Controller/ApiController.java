package Oregano.Backend.Controller;

import Oregano.Backend.DTO.FetchJobListingsDTO;
import Oregano.Backend.DTO.NumberOfRegisteredDisabledPeople;
import Oregano.Backend.ApiService.FetchJobListingsService;
import Oregano.Backend.ApiService.NumberOfRegisteredDisabledPeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
}

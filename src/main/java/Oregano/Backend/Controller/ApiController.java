package Oregano.Backend.Controller;

import Oregano.Backend.ApiService.EstimatedSixMonthsIncomesService;
import Oregano.Backend.DTO.EstimatedSixMonthsIncome;

import Oregano.Backend.DTO.FetchJobListingsDTO;
import Oregano.Backend.DTO.NumberOfRegisteredDisabledPeople;
import Oregano.Backend.ApiService.FetchJobListingsService;
import Oregano.Backend.ApiService.NumberOfRegisteredDisabledPeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;



@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ApiController {

    private final NumberOfRegisteredDisabledPeopleService numberOfRegisteredDisabledPeopleService;
    private final FetchJobListingsService fetchJobListingsService;

    private final EstimatedSixMonthsIncomesService estimatedSixMonthsIncomesService;

    @GetMapping("/filtered-disabled-people")
    public ResponseEntity<List<NumberOfRegisteredDisabledPeople>> getFilteredNumberOfRegisteredDisabledPeople() {
        return new ResponseEntity<>(numberOfRegisteredDisabledPeopleService.getFilteredNumberOfRegisteredDisabledPeople(), HttpStatus.OK);
    }

    @GetMapping("/EstimatedSixMonthsIncome")
    public ResponseEntity<Map<String,List<EstimatedSixMonthsIncome>>> getEstimatedSixMonthsIncomes() {
        return new ResponseEntity<>(estimatedSixMonthsIncomesService.getFilteredEstimatedSixMonthsIncome(), HttpStatus.OK);
    }


    @GetMapping("/fetchJobListings")
    public ResponseEntity<FetchJobListingsDTO.Items> callApi(
        @RequestParam(value = "region") String region
        ,@RequestParam(value = "empType") String empType
    ) {
        return new ResponseEntity<>(fetchJobListingsService.fetchJobListings(region, empType).getBody().getItems(), HttpStatus.OK);
    }
}
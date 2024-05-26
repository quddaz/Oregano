package Oregano.Backend.Controller;

import Oregano.Backend.DTO.NumberOfRegisteredDisabledPeople;
import Oregano.Backend.Service.NumberOfRegisteredDisabledPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class NumberOfRegisteredDisabledPeopleController {

    @Autowired
    private NumberOfRegisteredDisabledPeopleService numberOfRegisteredDisabledPeopleService;

    @GetMapping("/filtered-disabled-people")
    public Flux<NumberOfRegisteredDisabledPeople> getFilteredNumberOfRegisteredDisabledPeople() {
        return numberOfRegisteredDisabledPeopleService.getFilteredNumberOfRegisteredDisabledPeople();
    }
}

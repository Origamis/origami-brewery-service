package origamis.springframework.beerservice.service;

import origamis.springframework.beerservice.domain.BeerDto;

import java.util.UUID;

public interface BeerService {
    
    BeerDto getBeerById(UUID id);
}

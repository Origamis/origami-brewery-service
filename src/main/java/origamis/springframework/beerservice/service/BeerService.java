package origamis.springframework.beerservice.service;

import origamis.springframework.beerservice.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    
    BeerDto getBeerById(UUID id);
}

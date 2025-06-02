package origamis.springframework.beerservice.service;

import origamis.springframework.beerservice.model.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
    
    List<BeerDto> listBeers(Boolean showInventoryOnHand);
}

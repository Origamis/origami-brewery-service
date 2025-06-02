package origamis.springframework.beerservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import origamis.springframework.beerservice.exceptions.NotFoundException;
import origamis.springframework.beerservice.mappers.BeerMapper;
import origamis.springframework.beerservice.model.BeerDto;
import origamis.springframework.beerservice.repositories.BeerRepository;
import origamis.springframework.beerservice.service.BeerService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(value = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand) {
        System.out.println("Fetching beer with ID: " + beerId + " from database");
        
        if (showInventoryOnHand) {
            return beerMapper.toDtoWithInventory(beerRepository.findById(beerId).orElseThrow(
                    () -> new NotFoundException("Beer not found with ID: " + beerId)
            ));
        } else {
            return beerMapper.toDto(beerRepository.findById(beerId).orElseThrow(
                    () -> new NotFoundException("Beer not found with ID: " + beerId)
            ));
        }
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return beerMapper.toDto(beerRepository.save(beerMapper.toEntity(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        var beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Beer not found with ID: " + beerId));

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.toDto(beerRepository.save(beer));
    }

    @Cacheable(value = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public List<BeerDto> listBeers(Boolean showInventoryOnHand) {
        System.out.println("Fetching all beers from database");
        if (showInventoryOnHand) {
            return beerRepository.findAll().stream()
                    .map(beerMapper::toDtoWithInventory)
                    .toList();
        } else {
            return beerRepository.findAll().stream()
                    .map(beerMapper::toDto)
                    .toList();
        }
    }
}

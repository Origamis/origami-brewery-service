package origamis.springframework.beerservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import origamis.springframework.beerservice.exceptions.NotFoundException;
import origamis.springframework.beerservice.mappers.BeerMapper;
import origamis.springframework.beerservice.model.BeerDto;
import origamis.springframework.beerservice.repositories.BeerRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID id) {
        return beerMapper.toDto(beerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Beer not found with ID: " + id)
        ));
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
}

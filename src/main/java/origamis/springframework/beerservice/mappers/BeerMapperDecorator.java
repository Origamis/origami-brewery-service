package origamis.springframework.beerservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import origamis.springframework.beerservice.domain.Beer;
import origamis.springframework.beerservice.model.BeerDto;
import origamis.springframework.beerservice.service.BeerInventoryService;

public abstract class BeerMapperDecorator implements BeerMapper {
    
    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }
    
    @Override
    public BeerDto toDto(Beer beer) {
        return beerMapper.toDto(beer);
    }

    @Override
    public Beer toEntity(BeerDto beerDto) {
        return beerMapper.toEntity(beerDto);
    }

    @Override
    public BeerDto toDtoWithInventory(Beer beer) {
        BeerDto beerDto = beerMapper.toDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return beerDto;
    }
}

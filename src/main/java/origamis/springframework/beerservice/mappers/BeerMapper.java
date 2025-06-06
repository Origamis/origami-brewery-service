package origamis.springframework.beerservice.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import origamis.springframework.beerservice.domain.Beer;
import origamis.springframework.beerservice.model.BeerDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    
    BeerDto toDto(Beer beer);
    
    Beer toEntity(BeerDto beerDto);
    
    BeerDto toDtoWithInventory(Beer beer);
}

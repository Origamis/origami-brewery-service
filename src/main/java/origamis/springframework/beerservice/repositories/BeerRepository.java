package origamis.springframework.beerservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import origamis.springframework.beerservice.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}

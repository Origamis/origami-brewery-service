package origamis.springframework.beerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import origamis.springframework.beerservice.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Beer findBeerByUpc(String upc);
}

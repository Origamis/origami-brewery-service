package origamis.springframework.beerservice.service;

import java.util.UUID;

public interface BeerInventoryService {
    
    Integer getOnHandInventory(UUID beerId);
}
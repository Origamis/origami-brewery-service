package origamis.springframework.beerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import origamis.springframework.beerservice.model.BeerDto;
import origamis.springframework.beerservice.service.BeerInventoryService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerInventoryServiceImpl implements BeerInventoryService {

    private static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;
    
    @Value("${brewery.beer-inventory.url}")
    private String inventoryHost;
    
    @Override
    public Integer getOnHandInventory(UUID beerId) {
        
        var response = restTemplate.exchange(inventoryHost + INVENTORY_PATH, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<BeerDto>>() {}, beerId);
        
        return Objects.requireNonNull(response.getBody())
                .stream()
                .mapToInt(BeerDto::getQuantityOnHand)
                .sum();
    }
}

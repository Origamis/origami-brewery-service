package origamis.springframework.beerservice.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import origamis.springframework.beerservice.domain.Beer;
import origamis.springframework.beerservice.repositories.BeerRepository;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeers();
    }

    private void loadBeers() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .upc(BEER_1_UPC)
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .price(new BigDecimal("5.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .upc(BEER_2_UPC)
                    .quantityToBrew(100)
                    .minOnHand(10)
                    .price(new BigDecimal("8.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Corona Extra")
                    .beerStyle("PALE_ALE")
                    .upc(BEER_3_UPC)
                    .quantityToBrew(50)
                    .minOnHand(5)
                    .price(new BigDecimal("99.99"))
                    .build());

            System.out.println("Loaded Beers: " + beerRepository.count());
        }
    }
}

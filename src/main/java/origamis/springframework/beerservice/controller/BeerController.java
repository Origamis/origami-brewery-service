package origamis.springframework.beerservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import origamis.springframework.beerservice.model.BeerDto;
import origamis.springframework.beerservice.service.BeerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId, @RequestParam(required = false, defaultValue = "false") Boolean showInventoryOnHand) {
        return ResponseEntity.ok(beerService.getBeerById(beerId, showInventoryOnHand));
    }

    @PostMapping("beer")
    public ResponseEntity<BeerDto> saveBeer(@RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity<>(beerService.saveBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable UUID beerId, @Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("beer/list")
    public ResponseEntity<List<BeerDto>> listBeers(@RequestParam(required = false, defaultValue = "false") Boolean showInventoryOnHand) {
        return ResponseEntity.ok(beerService.listBeers(showInventoryOnHand));
    }

    @GetMapping("/beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUPC(@PathVariable String upc) {
        return ResponseEntity.ok(beerService.getBeerByUPC(upc));
    }
}
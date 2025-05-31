package origamis.springframework.beerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import origamis.springframework.beerservice.bootstrap.BeerLoader;
import origamis.springframework.beerservice.model.BeerDto;
import origamis.springframework.beerservice.model.BeerStyle;
import origamis.springframework.beerservice.service.BeerService;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeerService beerService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        // act and assert
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void saveBeer() throws Exception {
        // arrange
        var beerDto = createBeerDto();

        // act and assert
        mockMvc.perform(post("/api/v1/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        // arrange
        var beerDto = createBeerDto();

        // act and assert
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(status().isNoContent());
    }

    private BeerDto createBeerDto() {
        return BeerDto.builder()
                .beerName("Corona")
                .beerStyle(BeerStyle.ALE)
                .upc(BeerLoader.BEER_1_UPC)
                .price(new BigDecimal("9.99"))
                .build();
    }
}
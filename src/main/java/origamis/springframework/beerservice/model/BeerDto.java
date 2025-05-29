package origamis.springframework.beerservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {
    
    @Null
    private UUID id;
    
    @Null
    private Integer version;
    
    @NotBlank
    private String beerName;
    
    @Null
    private OffsetDateTime createdDate;
    
    @Null
    private OffsetDateTime lastModifiedDate;
    
    @NotNull
    private BeerStyle beerStyle;
    
    @Positive
    @NotNull
    private Long upc;
    
    @NotNull
    @Positive
    private BigDecimal price;
    
    private Integer quantityOnHand;
}

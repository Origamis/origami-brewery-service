package origamis.springframework.beerservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm:ssz", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
    
    @Null
    @JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm:ssz", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;
    
    @NotNull
    private BeerStyle beerStyle;

    @NotNull
    private String upc;
    
    @NotNull
    @Positive
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
    
    private Integer quantityOnHand;
}

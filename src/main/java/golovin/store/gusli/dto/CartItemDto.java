package golovin.store.gusli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartItemDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;
    private Integer quantity;

    @JsonProperty(access = READ_ONLY)
    private ProductDto product;

    @JsonProperty(access = WRITE_ONLY)
    private Long productId;
}

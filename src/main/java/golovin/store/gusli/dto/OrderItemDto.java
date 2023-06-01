package golovin.store.gusli.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItemDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0,  message = "Quantity must be greater than zero")
    private Integer quantity;

    @JsonProperty(access = WRITE_ONLY)
    private Long productId;

    @JsonProperty(access = READ_ONLY)
    private ProductDto product;

    @JsonProperty(access = READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = READ_ONLY)
    private Timestamp updatedAt;

}

package golovin.store.gusli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import golovin.store.gusli.entity.Status;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @JsonProperty(access = READ_ONLY)
    private Status status;

    @NotNull(message = "TotalCost is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "TotalCost must be greater than zero")
    private Double totalCost;

    @NotNull
    @NotEmpty
    private Set<OrderItemDto> items;

    @JsonProperty(access = READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = READ_ONLY)
    private Timestamp updatedAt;
}

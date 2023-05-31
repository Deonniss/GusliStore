package golovin.store.gusli.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDto {

    @JsonIgnoreProperties(allowGetters = true)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;

    @NotBlank(message = "Description is required")
    private String description;

    @JsonIgnoreProperties(allowGetters = true)
    private CategoryDto category;

    @JsonIgnoreProperties(allowGetters = true)
    private double avgRating;

    @JsonIgnoreProperties(allowGetters = true)
    private double totalReview;

    @JsonIgnoreProperties(allowGetters = true)
    private Timestamp createdAt;

    @JsonIgnoreProperties(allowGetters = true)
    private Timestamp updatedAt;
}

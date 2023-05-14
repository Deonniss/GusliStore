package golovin.store.gusli.dto;

import lombok.*;

import java.time.ZonedDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Double avgRating;
    private Double totalReview;
    private CategoryDto category;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}

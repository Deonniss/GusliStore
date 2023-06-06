package golovin.store.gusli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReviewDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @Positive(message = "rating must be positive 1-5")
    @Max(value = 5, message = "rating must be positive 1-5")
    private Integer rating;

    private String comment;

    @JsonProperty(access = READ_ONLY)
    private String authorName;

    @JsonProperty(access = READ_ONLY)
    private ProductDto product;

    @JsonProperty(access = READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = READ_ONLY)
    private Timestamp updatedAt;

}

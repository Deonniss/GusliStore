package golovin.store.gusli.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryDto {

    @JsonIgnoreProperties(allowGetters = true)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @JsonIgnoreProperties(allowGetters = true)
    private Timestamp createdAt;

    @JsonIgnoreProperties(allowGetters = true)
    private Timestamp updatedAt;
}

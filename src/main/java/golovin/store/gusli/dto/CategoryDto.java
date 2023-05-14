package golovin.store.gusli.dto;

import lombok.*;

import java.time.ZonedDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryDto {

    private Long id;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}

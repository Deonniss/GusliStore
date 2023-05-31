package golovin.store.gusli.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryDto {

    private Long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

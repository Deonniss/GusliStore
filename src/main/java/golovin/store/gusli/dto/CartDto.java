package golovin.store.gusli.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartDto {

    private Long id;
    private Integer totalProduct;
    private Double totalCost;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

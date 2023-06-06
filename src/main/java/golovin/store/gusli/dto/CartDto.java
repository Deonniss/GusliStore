package golovin.store.gusli.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartDto {

    private Long id;
    private Integer totalQuantity;
    private Double totalCost;
    private List<CartItemDto> items;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

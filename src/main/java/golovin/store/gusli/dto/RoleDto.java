package golovin.store.gusli.dto;

import golovin.store.gusli.entity.type.RoleType;
import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleDto {

    private Long id;
    private RoleType name;
}

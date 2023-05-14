package golovin.store.gusli.entity;

import golovin.store.gusli.entity.type.ParamType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "param")
public class Param {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ParamType type;
    private String name;
    private String description;
}

package golovin.store.gusli.entity;

import golovin.store.gusli.entity.type.StatusType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusType type;
    private String name;
    private String description;
}

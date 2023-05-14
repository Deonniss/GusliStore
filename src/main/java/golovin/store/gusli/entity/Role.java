package golovin.store.gusli.entity;

import golovin.store.gusli.entity.type.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RoleType name;
}
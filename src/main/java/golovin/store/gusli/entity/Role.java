package golovin.store.gusli.entity;

import golovin.store.gusli.entity.type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"users"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    @ToString.Exclude
    @JoinTable(name = "user_role",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "role_id"))
    private transient Set<User> users;

    private transient String authority;

    @Override
    public String getAuthority() {
        return name.name();
    }
}

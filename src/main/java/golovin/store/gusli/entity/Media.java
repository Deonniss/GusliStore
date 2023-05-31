package golovin.store.gusli.entity;

import golovin.store.gusli.entity.type.ExtensionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ExtensionType extension;
    private String filePath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}

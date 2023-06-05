package golovin.store.gusli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
@EntityListeners(AuditingEntityListener.class)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private Integer totalQuantity;

    private Double totalCost;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItem> items;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;

    public void addItem(CartItem cartItem) {
        this.items.add(cartItem);
    }

    public void addCost(Double cost) {
        if (cost <= 0) {
            throw new RuntimeException("The cost must be greater than 0");
        }
        this.totalCost += cost;
    }

    public void addQuantity(Integer quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("The quantity must be greater than 0");
        }
        this.totalQuantity += quantity;
    }

    public void minusCost(double cost) {
        if (cost > totalCost || cost <= 0) {
            throw new RuntimeException("The total cost should be greater than the cost price");
        }
        totalCost -= cost;
    }

    public void minusQuantity(Integer quantity) {
        if (quantity > totalCost || quantity <= 0) {
            throw new RuntimeException("The total quantity should be greater than the quantity");
        }
        this.totalQuantity -= quantity;
    }
}

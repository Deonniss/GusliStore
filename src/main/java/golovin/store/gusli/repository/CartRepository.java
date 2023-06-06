package golovin.store.gusli.repository;

import golovin.store.gusli.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);

    @Modifying
    @Query("delete from CartItem c where c.cart.id = :cartId")
    void clearCartById(@Param("cartId") Long cartId);
}

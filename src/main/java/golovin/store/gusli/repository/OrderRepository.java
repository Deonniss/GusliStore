package golovin.store.gusli.repository;

import golovin.store.gusli.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.id = :userId")
    Page<Order> getAllByUserId(Long userId, Pageable pageable);
}

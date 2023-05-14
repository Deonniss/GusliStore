package golovin.store.gusli.repository;

import golovin.store.gusli.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

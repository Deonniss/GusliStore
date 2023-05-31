package golovin.store.gusli.repository;

import golovin.store.gusli.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

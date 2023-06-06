package golovin.store.gusli.repository;

import golovin.store.gusli.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select count(r) > 0 from Review r where r.user.id = :userId and r.product.id = :productId")
    boolean existReview(@Param("userId") Long userId, @Param("productId") Long productId);
}

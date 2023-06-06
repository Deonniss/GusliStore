package golovin.store.gusli.repository;

import golovin.store.gusli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("select u.locked from User u where u.id = :userId")
    boolean isLocked(@Param("userId") Long userId);
}

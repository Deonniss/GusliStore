package golovin.store.gusli.repository;

import golovin.store.gusli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

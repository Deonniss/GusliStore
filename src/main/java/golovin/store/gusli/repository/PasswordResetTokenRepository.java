package golovin.store.gusli.repository;

import golovin.store.gusli.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
}

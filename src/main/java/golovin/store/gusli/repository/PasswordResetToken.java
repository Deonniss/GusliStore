package golovin.store.gusli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetToken extends JpaRepository<PasswordResetToken, Long> {
}

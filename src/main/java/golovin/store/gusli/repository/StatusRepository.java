package golovin.store.gusli.repository;

import golovin.store.gusli.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}

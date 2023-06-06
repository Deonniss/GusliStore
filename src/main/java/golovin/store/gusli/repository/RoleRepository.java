package golovin.store.gusli.repository;

import golovin.store.gusli.entity.Role;
import golovin.store.gusli.entity.type.RoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select u.roles from User u where u.id = :userId")
    Page<Role> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    Role findByName(RoleType type);
}

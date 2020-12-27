package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Role;
import com.telemedecine.telemedecine.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);

}

package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Role;
import com.telemedecine.telemedecine.domain.RoleName;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository {

    Optional<Role> findByName(RoleName roleName);

    List<Role> findAll();
}

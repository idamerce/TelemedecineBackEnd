package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Medecin;
import com.telemedecine.telemedecine.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String name);
    boolean existsByEmail(String email);

}

package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Medecin;
import com.telemedecine.telemedecine.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}

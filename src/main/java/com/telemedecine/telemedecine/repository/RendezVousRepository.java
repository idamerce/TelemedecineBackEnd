package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Medecin;
import com.telemedecine.telemedecine.domain.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
}

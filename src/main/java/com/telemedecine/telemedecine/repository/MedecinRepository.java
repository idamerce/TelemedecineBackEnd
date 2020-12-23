package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    List<Medecin> findAllBySpecialityDesignation(String designation);
    List<Medecin> findAllByHospitalName(String nom);
}

package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    List<Medecin> findAllBySpecialityDesignation(String designation);
    List<Medecin> findAllByHospitalName(String nom);
    Optional<Medecin> findById(Long id);

    boolean existsById(Long aLong);
}

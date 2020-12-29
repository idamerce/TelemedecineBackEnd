package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.ShiftHoraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftHoraireRepository extends JpaRepository<ShiftHoraire, Long> {

    boolean existsById(Long aLong);
}

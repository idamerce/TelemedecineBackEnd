package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Medecin;
import com.telemedecine.telemedecine.domain.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findAppointmentByDoctorByDay(long idMedecin, Date jour);
}

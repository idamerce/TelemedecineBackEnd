package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Medecin;
import com.telemedecine.telemedecine.domain.Patient;
import com.telemedecine.telemedecine.domain.RendezVous;
import com.telemedecine.telemedecine.domain.ShiftHoraire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findAppointmentByDoctorByDay(long idMedecin, Date jour);
    List<RendezVous> findAllByMedecin(Medecin medecin);
    List<RendezVous> findAllByPatient(Patient patient);
    List<RendezVous> findAllByMedecinAndDate(Medecin medecin, LocalDate date);

    Optional<RendezVous> findByMedecinAndDateAndShiftHoraire(Medecin medecin, LocalDate date, ShiftHoraire shiftHoraire);
}

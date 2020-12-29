package com.telemedecine.telemedecine.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_RENDEZVOUS_SEQ")
    @SequenceGenerator(name = "ID_RENDEZVOUS_SEQ", sequenceName = "ID_RENDEZVOUS_SEQ")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_id", nullable = true)
    private Medecin medecin;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_horaire_id", nullable = false)
    private ShiftHoraire shiftHoraire;

    private LocalDate date;
    private String description;



    private String location;


    private boolean isActive;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
    public RendezVous(Medecin medecin, Patient patient, ShiftHoraire shiftHoraire, LocalDate date, boolean isActive) {
        this.medecin = medecin;
        this.patient = patient;
        this.shiftHoraire = shiftHoraire;
        this.date = date;
        this.isActive = isActive;
    }
}

package com.telemedecine.telemedecine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "patient")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PATIENT_SEQ")
    @SequenceGenerator(name = "ID_PATIENT_SEQ", sequenceName = "ID_PATIENT_SEQ")
    private Long id;

    @NotNull
    private String nom;


    @NotNull
    private String prenom;
    @NotNull
    private String email;

    private Date dateNaissance;

    @NotNull
    @Column(unique = true)
    private String cin;
    @ManyToOne
    @NotNull
    private Ville ville;


    private String adresse;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "patient")
    @JsonIgnore
    private List<RendezVous> appointements = new ArrayList<>();
    public Patient(@NotBlank @Size(min = 5, max = 30) String nom, @NotBlank @Email String email) {
        this.nom = nom;
        this.email = email;
    }

    public Patient(String patientName) {
        this.nom=nom;
    }
}

package com.telemedecine.telemedecine.domain;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
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

    private Date dateNaissance;

    @NotNull
    @Column(unique = true)
    private String cin;
    @ManyToOne
    @NotNull
    private Ville ville;


    private String adresse;
}

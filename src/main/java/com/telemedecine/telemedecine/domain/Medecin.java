package com.telemedecine.telemedecine.domain;

import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MEDECIN_SEQ")
    @SequenceGenerator(name = "ID_MEDECIN_SEQ", sequenceName = "ID_MEDECIN_SEQ")
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
    @Transient
    private Boolean isActivated;

}

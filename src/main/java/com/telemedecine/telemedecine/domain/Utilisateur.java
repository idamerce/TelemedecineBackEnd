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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_UTILISATEUR_SEQ")
    @SequenceGenerator(name = "ID_UTILISATEUR_SEQ", sequenceName = "ID_UTILISATEUR_SEQ")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;
    @NotNull
    private String sexe;
    @ManyToOne
    @NotNull
    private Ville ville;


    private Date dateNaissance;

    @NotNull
    @Column(unique = true)
    private String cin;

    private String adresse;
    @Transient
    private Boolean isActivated;

}

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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_OUVRIER_SEQ")
    @SequenceGenerator(name = "ID_OUVRIER_SEQ", sequenceName = "ID_OUVRIER_SEQ")
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private Date dateNaissance;

    @NotNull
    @Column(unique = true)
    private String cin;


    private String adresse;
}

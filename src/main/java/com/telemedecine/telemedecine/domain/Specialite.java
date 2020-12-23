package com.telemedecine.telemedecine.domain;

import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_UTILISATEUR_SEQ")
    @SequenceGenerator(name = "ID_UTILISATEUR_SEQ", sequenceName = "ID_UTILISATEUR_SEQ")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String designation;
}

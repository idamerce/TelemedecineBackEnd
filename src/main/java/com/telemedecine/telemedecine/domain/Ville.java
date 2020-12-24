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

public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_VILLE_SEQ")
    @SequenceGenerator(name = "ID_VILLE_SEQ", sequenceName = "ID_VILLE_SEQ")
    private Long id;

    @NotNull
    private String nom;
}

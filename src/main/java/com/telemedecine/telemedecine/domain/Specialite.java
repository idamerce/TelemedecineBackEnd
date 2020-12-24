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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SPECIALITY_SEQ")
    @SequenceGenerator(name = "ID_SPECIALITY_SEQ", sequenceName = "ID_USPECIALITY_SEQ")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String designation;
}

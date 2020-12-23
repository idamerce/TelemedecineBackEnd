package com.telemedecine.telemedecine.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Hopital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_HOPITAL_SEQ")
    @SequenceGenerator(name = "ID_HOPITAL_SEQ", sequenceName = "ID_HOPITALSEQ")
    private Long id;



    @NotNull
    private String nom;
    @OneToMany
    private List<Medecin> medecins= new ArrayList<>();
}

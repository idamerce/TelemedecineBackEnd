package com.telemedecine.telemedecine.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Hopital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_HOPITAL_SEQ")
    @SequenceGenerator(name = "ID_HOPITAL_SEQ", sequenceName = "ID_HOPITALSEQ")
    private Long id;



    @NotNull
    private String name;
    @NotNull
    private String ville;


    @OneToMany(targetEntity=Medecin.class, mappedBy="hopital",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medecin> medecins = new ArrayList<>();
}

package com.telemedecine.telemedecine.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.Instant;

public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_RENDEZVOUS_SEQ")
    @SequenceGenerator(name = "ID_RENDEZVOUS_SEQ", sequenceName = "ID_RENDEZVOUS_SEQ")
    private Long id;

    private String name;


    private String description;

    private double price;

    private String location;

    private Instant rendezvousDateTime;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}

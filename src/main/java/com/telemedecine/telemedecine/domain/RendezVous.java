package com.telemedecine.telemedecine.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_RENDEZVOUS_SEQ")
    @SequenceGenerator(name = "ID_RENDEZVOUS_SEQ", sequenceName = "ID_RENDEZVOUS_SEQ")
    private Long id;

    private String name;


    private String description;

    private double price;

    private String location;

    private Date rendezvousDateTime;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}

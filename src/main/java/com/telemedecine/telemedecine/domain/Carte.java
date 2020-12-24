package com.telemedecine.telemedecine.domain;

import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_CARTE_SEQ")
    @SequenceGenerator(name = "ID_CARTE_SEQ", sequenceName = "ID_CARTE_SEQ")
    private Long id;

    @NotNull
    private Date dateOfCreation;



    @OneToMany(mappedBy = "carte", cascade = CascadeType.ALL)
    private List<Consultation> consultations;
}

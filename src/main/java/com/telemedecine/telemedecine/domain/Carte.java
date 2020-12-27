package com.telemedecine.telemedecine.domain;


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

    @NonNull
    private Date dateOfCreation;



   // @OneToMany(mappedBy = "carte", cascade = CascadeType.ALL)
  //  private List<Consultation> consultations;
}

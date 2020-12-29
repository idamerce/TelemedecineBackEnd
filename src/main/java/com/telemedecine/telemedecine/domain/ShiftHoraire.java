package com.telemedecine.telemedecine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@Table(name = "shift_horaires")
@NoArgsConstructor
@AllArgsConstructor
public class ShiftHoraire {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "patient")
    @JsonIgnore
    private List<RendezVous> appointements = new ArrayList<>();


    public ShiftHoraire(Long id, LocalTime timeStart, LocalTime timeEnd) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
}

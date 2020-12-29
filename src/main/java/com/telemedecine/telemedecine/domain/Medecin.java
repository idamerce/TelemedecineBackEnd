package com.telemedecine.telemedecine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "medecin")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Medecin  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone_number")
    private String phone;
    @Column(name = "address")
    @Size(max = 100)
    private String address;
    private Date dateNaissance;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "medecin")
    @JsonIgnore
    private List<RendezVous> appointements = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private Utilisateur user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="specialite_id", nullable=false)
    @JsonIgnore
    private Specialite specialite;

    public Medecin(String fullName, String phone, @Size(max = 100) String address, Utilisateur user) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.user = user;
    }

}

package com.telemedecine.telemedecine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.telemedecine.telemedecine.domain.audit.DateAudit;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "login"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Utilisateur extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_UTILISATEUR_SEQ")
    @SequenceGenerator(name = "ID_UTILISATEUR_SEQ", sequenceName = "ID_UTILISATEUR_SEQ")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;
    @NotNull
    private String sexe;
    //@ManyToOne
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ville_id", nullable=false)
    @JsonIgnore
    private Ville ville;

    private Date dateNaissance;

    @NotNull
    @Column(unique = true)
    private String cin;
   @NotNull
   private String  password;
    private String adresse;
    @Transient
    private Boolean isActivated;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public Utilisateur(String login, String nom, String prenom,
                       String sexe, Ville ville, Date dateNaissance,
                       String cin,String adresse,String password) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.ville=ville;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
        this.adresse = adresse;
        this.password = password;
    }

    public Utilisateur(String login, String nom, String prenom, String sexe, String ville, Date dateNaissence, String cin, String adresse, String encode) {
        super();
    }
}

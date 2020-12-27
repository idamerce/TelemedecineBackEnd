package com.telemedecine.telemedecine.domain;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
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
    @NotNull
    private String ville;

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
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public Utilisateur(String login, String nom, String prenom,
                       String sexe, String ville, Date dateNaissance,
                       String cin,String adresse,String password) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.ville = ville;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
        this.adresse = adresse;
        this.password = password;
    }
}

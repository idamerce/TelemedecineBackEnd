package com.telemedecine.telemedecine.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatientDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String nom;
    @JsonView(UserView.Basic.class)
    private String prenom;
    @JsonView(UserView.Basic.class)
    private Date dateNaissance;
    @JsonView(UserView.Basic.class)
    private String cin;
    @JsonView(UserView.Basic.class)
    private String adresse;
}

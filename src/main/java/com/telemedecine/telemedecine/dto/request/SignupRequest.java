package com.telemedecine.telemedecine.dto.request;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;



 @Data
public class SignupRequest {
     private String login;
     @NotBlank
      private String nom;
      private String prenom;
     private String sexe;
     private String ville;
     private String adresse;
     private Date dateNaissence;
     private String cin;
     @NotBlank
     @NaturalId
     @Size(max = 40)
     @Email
     private String email;
    private List<String> role;
     @NotBlank
     @Size(min = 5, max = 30)
    private String password;

    
}

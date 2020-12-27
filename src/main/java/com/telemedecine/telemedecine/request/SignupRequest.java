package com.telemedecine.telemedecine.request;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;



 @Data
public class SignupRequest {
     private String login;
      private String nom;
      private String prenom;
     private String sexe;
     private String ville;
     private String adresse;
     private Date dateNaissence;
     private String cin;
    private List<String> role;
    private String password;

    
}

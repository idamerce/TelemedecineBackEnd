package com.telemedecine.telemedecine.reponse;

import com.telemedecine.telemedecine.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String login;
	private String nom;
	private String prenom;
	private String ville;
	private String adresse;
	private String sexe;
	private String cin;
	private Date dateNaissance;
	private List<Role> roles;

	public JwtResponse(String accessToken,
					   Long id,
					   String login,
					   String nom,
					   String prenom,
					   String ville,
					   String adresse,
					   String sexe,
					   String cin,
					   Date dateNaissance,
					   List<Role> roles) {
		this.token = accessToken;
		this.id = id;
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.adresse = adresse;
		this.sexe = sexe;
		this.cin = cin;
		this.dateNaissance = dateNaissance;
		this.roles = roles;

	}
	
}
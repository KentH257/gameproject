package org.gameproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/*
 * 
 * @Entity declare the class as an Entity
 *  it need to get an empty constructor
 *  can't be a final / an interface / an enum  class but she can be abstract
 *  This class is the most important things that will be the "reflection" of the Database
 *  
 */
@Entity
@Table(name ="utilisateurs")
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	
	@Pattern(regexp = "^[a-zA-Z,.'-]+$")
	@Size(min=4, max=15)
	@NotBlank
	@Column(name ="identifiant" )
	private String identifiant;
	

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$")
	@Size(min=4)
	@NotBlank
	@Column(name ="mot_de_passe")
	private String mot_de_passe;
	

	
	@Size(min=4)
	@NotBlank
	@Email
	@Column(name ="email", unique = true)
	private String email;
	
	public Utilisateur() {
		
	}
	public Utilisateur(String identifiant) {
		this.identifiant = identifiant;
	}
	public Utilisateur(String identifiant, String mot_de_passe) {
		this.identifiant = identifiant;
		this.mot_de_passe = mot_de_passe;
	}
	public Utilisateur(String identifiant, String mot_de_passe, int id)
	{
		super();
		this.identifiant = identifiant;
		this.mot_de_passe = mot_de_passe;
		this.id = id;
	}
	public Utilisateur(String identifiant, String mot_de_passe, String email, int id) {
		this.identifiant = identifiant;
		this.mot_de_passe = mot_de_passe;
		this.email = email;
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	/**
	 * @return the mot_de_passe
	 */
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	/**
	 * @param mot_de_passe the mot_de_passe to set
	 */
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}

package org.gameproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="imagepath")
public class UtilisateurImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="imagePath")
	private String imagePath;
	
	@Column(name = "utilisateurs_id")
	private int utilisateurs_id;

	public UtilisateurImage() {}
	public UtilisateurImage(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getUtilisateurs_id() {
		return utilisateurs_id;
	}

	public void setUtilisateurs_id(int utilisateurs_id) {
		this.utilisateurs_id = utilisateurs_id;
	}



}

package org.gameproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "photo")
public class UploadFile {
   
	private int id;
	
	@Pattern(regexp = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.jpg|.png|.gif|.bmp)$")
    private String description;
    
    
    private byte[] image;
 
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }
 
    public void setImage(byte[] image) {
        this.image = image;
    }
}
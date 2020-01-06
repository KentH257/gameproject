package org.gameproject.service;

import java.util.List;

import org.gameproject.entities.Utilisateur;
import org.gameproject.entities.UtilisateurImage;
import org.gameproject.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
/*
 * There we stack our different method that'll be implement by UserServiceImpl
 */
@Service
public interface UserService {

    public List < Utilisateur > getUtilisateurs();

    public void saveUtilisateur(Utilisateur identifiant, UtilisateurImage imagePath);

    public Utilisateur getUtilisateur(int theId) throws ResourceNotFoundException;

    public void deleteUtilisateur(int theId) throws ResourceNotFoundException;
    
 // [USER,ADMIN,..]
    public List<String> getUserRoles(String identifiant);
    
    public List<Utilisateur> UserList(Utilisateur identifiant);
    
    public Utilisateur findUserByUsername(String identifiant);
    
    public String getImagePathById(int id);
    
    public void updateImage(UtilisateurImage imagePath);
    
    public void deleteUtilisateurImage(int theId);
    
    public UtilisateurImage findIdByUserId(int userId);
    
    public void updateUtilisateur(Utilisateur identifiant);
    
}
package org.gameproject.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * UtilisateurInfoMapper is used to map the columns of the SQL instruction from the Utilisateur fields
 * we took only the Identifiant and the Mot_de_passe field there
 * 
 * RowMapper interface allows to map a row of the relations with the instance of user-defined class. 
 * It iterates the ResultSet internally and adds it into the collection
 */
public class UtilisateurInfoMapper implements RowMapper<Utilisateur> {
 
    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        String identifiant = rs.getString("Identifiant");
        String mot_de_passe = rs.getString("Mot_de_passe");
        String email = rs.getString("Email");
        int id = rs.getInt("Id");
 
        return new Utilisateur(identifiant, mot_de_passe, email, id);
    }
 
}
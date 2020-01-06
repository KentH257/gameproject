package org.gameproject.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UtilisateurImageInfoMapper implements RowMapper<UtilisateurImage> {
 
    @Override
    public UtilisateurImage mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        
        
        int id = rs.getInt("Id");
 
        return new UtilisateurImage(id);
    }
 
}
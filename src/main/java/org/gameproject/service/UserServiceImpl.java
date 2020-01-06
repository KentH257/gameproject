package org.gameproject.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.gameproject.entities.Utilisateur;
import org.gameproject.entities.UtilisateurImage;
import org.gameproject.entities.UtilisateurImageInfoMapper;
import org.gameproject.entities.UtilisateurInfoMapper;
import org.gameproject.exception.ResourceNotFoundException;
import org.gameproject.repository.UploadRepository;
import org.gameproject.repository.UserImageRepository;
import org.gameproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
 * @Transactionnal : transaction handle auto, it means we don't need : 
 * 								UserTransaction utx = entityManager.getTransaction(); 
 *									utx.begin();
 * 									save();
 *									utx.commit(); 
 *									anymore
 * 
 * JdbcDaoSupport : 
 * 
 */
@Service
@Transactional
public class UserServiceImpl extends JdbcDaoSupport implements UserService {

	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserImageRepository userImageRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    public UserServiceImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	// ********************** METHOD FOR USER **********************
	
    @Override
    @Transactional
    public List < Utilisateur > getUtilisateurs() {
        return userRepository.findAll();
    }
    // ********************** SAVE USER / IMAGE **********************
    @Override
    @Transactional
    public void saveUtilisateur(Utilisateur identifiant, UtilisateurImage imagePath) {
    	
    		try {
    			if(!imagePath.getImagePath().isEmpty()) 
        		{
    			
        		userImageRepository.save(imagePath);
        		}
    	} catch (Exception e) {
			System.out.println("Utilisateur " + identifiant + " already exists");
		}
    		identifiant.setMot_de_passe(passwordEncoder.encode(identifiant.getMot_de_passe()));
    		userRepository.save(identifiant);
}
    // **********************  UPDATE USER **********************
    @Override
    @Transactional
    public void updateUtilisateur(Utilisateur identifiant) {
    	
    	userRepository.save(identifiant);
    }
    // ********************** RETRIEVE USER BY ID **********************
    @Override
    @Transactional
    public Utilisateur getUtilisateur(int id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException(id));
    }

public void updateImage(UtilisateurImage imagePath) {
    	
    		userImageRepository.save(imagePath);
    }
    
    
	// ********************** DELETE USER BY ID **********************
    @Override
    @Transactional
    public void deleteUtilisateur(int theId) {
        userRepository.deleteById(theId);
    }
 
    // ********************** RETRIEVE ROLE OF USER (Simple user or admin) **********************
    @Override
    public List<String> getUserRoles(String identifiant) {
        String sql = "Select r.User_Role "//
                + " from User_Roles r where r.Identifiant = ? ";
         
        Object[] params = new Object[] { identifiant };
         
        List<String> roles = this.getJdbcTemplate().queryForList(sql,params, String.class);
         
        return roles;
    }
    // ********************** LIST OF USER AND THEIR DATA **********************
    @Override
	public List<Utilisateur> UserList(Utilisateur identifiant) {

		List<Utilisateur> list = this.getJdbcTemplate().query("SELECT * FROM utilisateurs", new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur identifiant = new Utilisateur();
				identifiant.setId(rs.getInt("id"));
				identifiant.setIdentifiant(rs.getString("identifiant"));
				identifiant.setMot_de_passe(rs.getString("mot_de_passe"));
				identifiant.setEmail(rs.getString("email"));

				return identifiant;
			}
		});

		return list;
	}
    // ********************** RETRIEVE USER DATA BY HIS USERNAME **********************
    @Override
    public Utilisateur findUserByUsername(String identifiant) {
        String sql = "Select u.Identifiant,u.Mot_de_passe, u.Email, u.Id "//
                + " from Utilisateurs u where u.Identifiant = ? ";
 
        Object[] params = new Object[] { identifiant };
        UtilisateurInfoMapper mapper = new UtilisateurInfoMapper();
        try {
            Utilisateur utilisateur = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return utilisateur;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // 			********************** METHOD FOR IMAGE **********************
    
    // ********************** RETRIEVE IMAGE PATH WITH USER ID **********************
    @Override
    public String getImagePathById(int id) {
    	
    	String query = "SELECT imagepath.imagePath FROM imagepath INNER JOIN utilisateurs ON utilisateurs.id = imagepath.utilisateurs_id WHERE utilisateurs.id = ?";

    	String imagePath = this.getJdbcTemplate().queryForObject(query,  new Object[] {id}, String.class);
    	
    	return imagePath;
    }
    @Override
    @Transactional
    public void deleteUtilisateurImage(int theId) {
    	
        userImageRepository.deleteById(theId);
        
    }
    // ********************** FIND THE ID OF THE PATH WITH THE USER ID **********************
    @Override
    public UtilisateurImage findIdByUserId(int userId) {
        String sql = "Select imagepath.Id "//
                + " from imagepath where imagepath.utilisateurs_id = ? ";
 
        Object[] params = new Object[] { userId };
        UtilisateurImageInfoMapper mapper = new UtilisateurImageInfoMapper();
        try {
            UtilisateurImage utilisateurImage = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return utilisateurImage;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

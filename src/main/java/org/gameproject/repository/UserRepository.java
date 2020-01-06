package org.gameproject.repository;

import org.gameproject.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @Repository marks the specific class as a DAO
 *  This interface acts primarily as a marker interface to capture the types 
 *  to work with and to help you to discover interfaces that extend this one
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<Utilisateur, Integer> {
	
	

}

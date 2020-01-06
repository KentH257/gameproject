package org.gameproject.repository;

import org.gameproject.entities.UtilisateurImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userImageRepository")
public interface UserImageRepository extends JpaRepository<UtilisateurImage, Integer> {
	
	

}

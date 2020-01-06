package org.gameproject.repository;

import org.gameproject.entities.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("uploadRepository")
public interface UploadRepository extends JpaRepository<UploadFile, Integer> {

}
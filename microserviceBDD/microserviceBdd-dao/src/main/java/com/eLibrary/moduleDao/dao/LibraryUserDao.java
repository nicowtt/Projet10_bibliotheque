package com.eLibrary.moduleDao.dao;

import com.eLibrary.moduleModel.beans.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserDao extends JpaRepository<LibraryUser, Integer> {

    LibraryUser findByUserEmail(String userEmail);
}

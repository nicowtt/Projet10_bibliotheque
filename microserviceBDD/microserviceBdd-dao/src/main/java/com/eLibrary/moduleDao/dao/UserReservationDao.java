package com.eLibrary.moduleDao.dao;

import com.eLibrary.moduleModel.beans.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReservationDao extends JpaRepository<UserReservation, Integer> {
}

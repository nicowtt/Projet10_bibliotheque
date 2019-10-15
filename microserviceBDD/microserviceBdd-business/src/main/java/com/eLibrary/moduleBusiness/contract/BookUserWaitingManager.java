package com.eLibrary.moduleBusiness.contract;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookUserWaitingManager {
    List<Integer> updateUsersStand(int bookId);
}

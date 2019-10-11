package com.eLibrary.moduleBusiness.contract;

import org.springframework.stereotype.Service;

@Service
public interface BookUserWaitingManager {
    void updateUsersStand(int bookId);
}

package com.student.pantry.studentPantry.service;

import org.springframework.stereotype.Service;

import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.dto.UserRole;

@Service
public class AdminLoginManager {
    private static AdminLoginManager instance;
    private int adminCount;

    private AdminLoginManager() {
        adminCount = 0;
    }

    public static synchronized AdminLoginManager getInstance() {
        if (instance == null) {
            instance = new AdminLoginManager();
        }
        return instance;
    }

    public boolean login(UserDto userDto) {
        if (userDto.getUserrole().equals(UserRole.ADMIN) && adminCount >= 1) {
            return false;
        } else {

            if (userDto.getUserrole().equals(UserRole.ADMIN)) {
                adminCount = 1;
            }

            return true;
        }
    }
}


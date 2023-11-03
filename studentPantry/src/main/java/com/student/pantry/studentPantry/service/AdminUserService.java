package com.student.pantry.studentPantry.service;

import com.student.pantry.studentPantry.dto.AdminUserDto;

public interface AdminUserService {
     public AdminUserDto getUserByEmail(String emailString);
     public AdminUserDto getUserByEmailAndPassworDto(String email, String passwd);
}

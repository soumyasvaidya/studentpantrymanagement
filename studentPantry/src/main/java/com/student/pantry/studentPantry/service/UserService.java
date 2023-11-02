package com.student.pantry.studentPantry.service;

import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.response.UserResponse;
public interface UserService {
   // AdminUser adminUser;
   // private static int adminUserCount=0;

    public UserResponse login(UserDto userDto);
    public UserResponse logout(UserDto userDto);
    
}

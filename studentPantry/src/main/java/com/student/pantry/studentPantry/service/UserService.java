package com.student.pantry.studentPantry.service;

import java.util.List;

import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.entity.PantryUser;
import com.student.pantry.studentPantry.response.UserResponse;
public interface UserService {

    public UserResponse login(UserDto userDto);
    
    public UserResponse logout(UserDto userDto);
    
    public String getUserDetailsByUserId(long userId);
    
    public List<PantryUser> getAllUsers();
    
}

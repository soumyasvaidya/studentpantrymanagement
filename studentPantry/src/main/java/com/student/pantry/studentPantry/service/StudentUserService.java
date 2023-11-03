package com.student.pantry.studentPantry.service;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.entity.User;

public interface StudentUserService {
    public UserDto addUser( StudentUserDto userDto);
    public UserDto getUserByEmail(String emailString);
    public void removeUserByEmail(String emailString);
    public UserDto updateUser(UserDto userDto);
    public User getUserByEmailAndPassword(String email, String passwd);

}

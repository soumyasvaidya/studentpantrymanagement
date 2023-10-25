package com.student.pantry.studentPantry.service;

import org.springframework.stereotype.Service;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.entity.StudentUser;
import com.student.pantry.studentPantry.factory.UserDtoFactory;
import com.student.pantry.studentPantry.factory.UserEntityFactory;

@Service
public class StudentUserServiceImpl implements StudentUserService{
    StudentUser user;
    StudentUserDto studentUserDtouserDto;
    UserEntityFactory userEntityFactory;
    UserDto userDto;
    UserDtoFactory userDtoFactory;
   // @Autowired
   // StudentUserJpa studentuserjpa;

    public StudentUserServiceImpl() {
    }

    public UserDto addUser(StudentUserDto userDto){
        user= (StudentUser)UserEntityFactory.createUser(userDto.getRole(),userDto.getUsername() , userDto.getEmail(), userDto.getPassword(),userDto.getStudentId());
       // studentuserjpa.save(user);
        userDto = UserDtoFactory.createStudentDTO(userDto.getUsername(), userDto.getEmail(), null, userDto.getStudentId());
        return userDto;
    }
    public UserDto getUserByEmail(String emailString){
        return null;
    }
    public void removeUserByEmail(String emailString){
        
    }
    public UserDto updateUser(UserDto userDto){
        return null;
    }
}

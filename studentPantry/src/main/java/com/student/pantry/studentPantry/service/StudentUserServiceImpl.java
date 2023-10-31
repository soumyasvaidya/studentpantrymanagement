package com.student.pantry.studentPantry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.entity.StudentUser;
import com.student.pantry.studentPantry.entity.User;
import com.student.pantry.studentPantry.factory.UserDtoFactory;
import com.student.pantry.studentPantry.factory.UserEntityFactory;
import com.student.pantry.studentPantry.repository.UserJpa;


@Service
public class StudentUserServiceImpl implements StudentUserService{
    StudentUser user;
    StudentUserDto studentUserDtouserDto;
    UserEntityFactory userEntityFactory;
    UserDto userDto;
    UserDtoFactory userDtoFactory;
    @Autowired
    UserJpa userJpa;


    public StudentUserServiceImpl() {
    }

    public UserDto addUser(StudentUserDto userDto){
        user= (StudentUser)UserEntityFactory.createUser(userDto.getUserrole(),userDto.getUsername() , userDto.getEmail(), userDto.getUserPasswd(),userDto.getStudentId());
       // studentuserjpa.save(user);
        userDto = UserDtoFactory.createStudentDTO(userDto.getUsername(), userDto.getEmail(), null, userDto.getStudentId());
        return userDto;
    }
    public UserDto getUserByEmail(String emailString){
        return null;
    }

    public User getUserByEmailAndPassword(String email, String passwd){
        try{
        User user=userJpa.findByEmailAndUserPasswd(email, passwd);
        if(user==null){
            System.out.println("Invalid user");
            return null;
        }
        return user;
    }
    catch(Exception e){
        System.out.println("exception" + e.fillInStackTrace());
        return null;
    }

    }
    public void removeUserByEmail(String emailString){
        
    }
    public UserDto updateUser(UserDto userDto){
        return null;
    }
}

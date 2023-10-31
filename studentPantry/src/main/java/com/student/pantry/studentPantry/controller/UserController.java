package com.student.pantry.studentPantry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.service.StudentUserService;
import com.student.pantry.studentPantry.service.StudentUserServiceImpl;
import com.student.pantry.studentPantry.service.UserServiceImpl;


@RestController
class UserController{

    public UserController() {
    }

    @PostMapping("/register")
    public StudentUserDto register(){
        StudentUserService service=new StudentUserServiceImpl();
        StudentUserDto resp=(StudentUserDto)service.addUser(null);
        return resp;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        UserServiceImpl userServiceImpl =new UserServiceImpl();
        String role=null;
    
       System.out.println("role:"+userDto.getUserrole());
        Long resp=userServiceImpl.login(userDto);
        if(resp ==0){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username or password");
        }
        return ResponseEntity.ok().body("LOGIN SUCCESS");
    }

    @GetMapping("/users")
    public List<String> getAllUsers()
    {
        return new ArrayList<String>();
    }



}


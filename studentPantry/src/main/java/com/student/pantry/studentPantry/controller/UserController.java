package com.student.pantry.studentPantry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.service.StudentUserService;
import com.student.pantry.studentPantry.service.StudentUserServiceImpl;


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
    public String login(){
        return "login success";
    }

    @GetMapping("/users")
    public List<String> getAllUsers()
    {
        return new ArrayList<String>();
    }



}


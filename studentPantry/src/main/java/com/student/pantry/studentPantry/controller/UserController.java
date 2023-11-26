package com.student.pantry.studentPantry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.response.UserResponse;
import com.student.pantry.studentPantry.service.UserServiceImpl;


@RestController
class UserController{

    @Autowired
    UserServiceImpl userServiceImpl;
    public UserController() {
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody StudentUserDto userDto){
        //StudentUserService service=new StudentUserServiceImpl();
        System.out.println("register::"+userDto.toString());
        if(userDto.getEmail().equals("") || userDto.getEmail()==null || userDto.getUsername().equals("") || userDto.getUsername()==null){
            return ResponseEntity.badRequest().body("Provide username and Useremail");
        }
        UserResponse resp=userServiceImpl.registerUser(userDto);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
       // UserServiceImpl userServiceImpl =new UserServiceImpl();
        String role=null;
    
       System.out.println("role:"+userDto.getUserrole());
        UserResponse resp=userServiceImpl.login(userDto);
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/users")
    public List<String> getAllUsers()
    {
        return new ArrayList<String>();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long userId)
    {
        String message="";
        UserDto resp=null;
        try{
        resp= userServiceImpl.getUserByUserId(userId);
            message= "Get User Details successfull";
        
        }
        catch(Exception e){
            message= "Failed to fetch User Details ";
        }
        return ResponseEntity.ok().body( new UserResponse(message,resp));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UserDto userDto){
        // UserServiceImpl userServiceImpl =new UserServiceImpl();
    
        UserResponse resp=userServiceImpl.logout(userDto);
       
        return ResponseEntity.ok().body(resp);
        

    }



}


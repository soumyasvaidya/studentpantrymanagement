package com.student.pantry.studentPantry.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.dto.UserRole;
import com.student.pantry.studentPantry.entity.PantryUser;
import com.student.pantry.studentPantry.entity.PantryUserBuilder;
import com.student.pantry.studentPantry.factory.UserDtoFactory;
import com.student.pantry.studentPantry.repository.AdminUserJpa;
import com.student.pantry.studentPantry.repository.PantryUserRepository;
import com.student.pantry.studentPantry.repository.UserJpa;
import com.student.pantry.studentPantry.response.UserResponse;

@Service
public class UserServiceImpl implements UserService{
    public static int adminCount=0;
    AdminUserJpa adminUserJpa;
    UserJpa UserJpa;
    private final PantryUserRepository pantryUserJpa;
    
    private final AdminLoginManager adminLoginManager;

    @Autowired
    public UserServiceImpl(PantryUserRepository pRepository,AdminLoginManager adminLoginManager){
        pantryUserJpa=pRepository;
        this.adminLoginManager=adminLoginManager;

    }

    @Transactional
    public UserResponse registerUser(StudentUserDto userDto){
        PantryUser spPantryUser=null;
        System.out.println("user role::"+userDto.getUserrole());

        if(userDto.getUserrole().equals(UserRole.STUDENT)){
            System.out.println("student role::"+ userDto.getUserrole());
            spPantryUser=new PantryUserBuilder.Builder()
            .userId(userDto.getStudentId())
            .username(userDto.getUsername())
            .email(userDto.getEmail())
            .userPasswd(userDto.getUserPasswd())
            .userRole(com.student.pantry.studentPantry.entity.UserRole.STUDENT)
            .build();
            PantryUser pantryUser=null;
            if(userDto.getEmail()!=null){
                pantryUser=pantryUserJpa.findByEmail(userDto.getEmail());
            }
           if( pantryUser==null){
            pantryUserJpa.save(spPantryUser);
           }
           else {
            System.out.println("duplicate registration");
            return new UserResponse("duplicate registration", null);
           }

        }
        if(spPantryUser!=null){
        UserDto userresp= UserDtoFactory.createUserDTO(UserRole.STUDENT,spPantryUser.getUsername(),spPantryUser.getEmail(), null,spPantryUser.getUserId());
        return new UserResponse("Registration Successfull", userresp);
         }
    return new UserResponse("Registration Not possible",null);
}

    
    public UserResponse login(UserDto userDto){
        String message="";
        PantryUser pantryUser=null;
        try{
        System.out.println("admincount: "+adminCount);
        System.out.println("role::"+ userDto.getUserrole());
        pantryUser=pantryUserJpa.findByEmailAndUserPasswd(userDto.getEmail(), userDto.getUserPasswd());
        if(pantryUser!=null && pantryUser.getEmail()!=null){
            message="LOGIN SUCESS";
            userDto.setId(pantryUser.getUserId());
            userDto.setUsername(pantryUser.getUsername());
            if(adminLoginManager.login(userDto)==false){
            message="Only one admin login allowed at a time ";
        }
            System.out.println("admincount: "+adminCount);

        }
        else{
            message="INVALID USERNAME OR PASSWORD";
        }
    
    }
    catch(Exception e){
        System.err.println("ERROR LOGGING IN"+ e.getLocalizedMessage());
        message="ERROR LOGGING IN";
    }
    return new UserResponse(message,userDto);
        
    
}

    public UserResponse logout(UserDto userDto){
        String message="";
        try{
        PantryUser pantryUser=pantryUserJpa.findByEmail(userDto.getEmail());
        if(pantryUser!=null && pantryUser.getEmail()!=null){
            message="You have been logged out ";
             if(pantryUser.getUserrole().equals(com.student.pantry.studentPantry.entity.UserRole.ADMIN)){
                adminLoginManager.logout();
                
        }

        }
        else{
            message="INVALID USERNAME";
        }
    }
    catch(Exception e){
        System.err.println("ERROR LOGGING OUT"+ e.getLocalizedMessage());
        message="ERROR LOGGINg OUT";
    }
    return new UserResponse(message,null);
       
    }


    

    public String getUserDetailsByUserId(long userId) {
    	PantryUser pantryUser=pantryUserJpa.findById(userId);
    	//System.out.println("pantry user::"+ pantryUser.getEmail()+" :role:"+pantryUser.getUserrole());
        if(pantryUser==null){
            return null;
        }
        return pantryUser.getEmail();
    }

    public UserDto getUserByUserId(long userId){
        PantryUser pantryUser=pantryUserJpa.findById(userId);
        UserRole role;
    	System.out.println("pantry user::"+ pantryUser.getEmail()+" :role:"+pantryUser.getUserrole());
        if(pantryUser.getUserrole().equals(com.student.pantry.studentPantry.entity.UserRole.ADMIN)){
                role=UserRole.ADMIN;
        }
        else{
            role=UserRole.STUDENT;
        }
        return UserDtoFactory.createUserDTO(role,pantryUser.getUsername(), pantryUser.getEmail(), null, pantryUser.getUserId());
        
    }

    public List<PantryUser> getAllUsers() {
        return pantryUserJpa.findAll();
    }
    
    public List<String> getAllUserEmails() {
    	System.out.println("Inside getAllUSerEmails");
        List<PantryUser> users = getAllUsers();
        System.out.println(users);
        // Extract emails from users
        return users.stream()
                .map(PantryUser::getEmail)
                .collect(Collectors.toList());
    }

}

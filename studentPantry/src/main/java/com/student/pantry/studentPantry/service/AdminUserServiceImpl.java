package com.student.pantry.studentPantry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.pantry.studentPantry.dto.AdminUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.dto.UserRole;
import com.student.pantry.studentPantry.entity.AdminUser;
import com.student.pantry.studentPantry.entity.User;
import com.student.pantry.studentPantry.factory.UserDtoFactory;
import com.student.pantry.studentPantry.factory.UserEntityFactory;
import com.student.pantry.studentPantry.repository.AdminUserJpa;
import com.student.pantry.studentPantry.repository.UserJpa;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    AdminUserJpa adminUserJpa;
    @Autowired
    UserJpa userJpa;
    @Autowired
    UserDtoFactory userDtoFactory;

    @Autowired
    public AdminUserServiceImpl(){

    }

    @Override
    public AdminUserDto getUserByEmail(String emailString) {
        // TODO Auto-generated method stub
        AdminUser user = adminUserJpa.findByEmail(emailString);
        return (AdminUserDto)UserDtoFactory.createUserDTO(UserRole.ADMIN, user.getUsername(), user.getEmail(), null, user.getAdminId());
        
    }

    @Transactional
    @Override
    public AdminUserDto getUserByEmailAndPassworDto(String email, String passwd) {
        // TODO Auto-generated method stub
        User user=null;
        try{
        user=userJpa.findByEmailAndUserPasswd(email, passwd);
        System.out.println("DB data for admin:"+user.toString());
        if(user==null){
            User adminUser=UserEntityFactory.createUser(UserRole.ADMIN, "admin", email, passwd, 001L);
            
            if(adminUser!=null){
                System.out.println(adminUser.getEmail()+""+adminUser.getUserPasswd() +"" + adminUser.getUsername()+""+adminUser.getUserrole()+ adminUser.getUserId());
                
            adminUserJpa.save((AdminUser)adminUser);
            user=adminUser;
            
            }
            else{
                System.out.println("admin user create is null:: "+adminUser.toString());
            }
        }
        }
        catch(Exception e){
    
    }
    if(user!=null){
    UserDto admin= UserDtoFactory.createUserDTO(UserRole.ADMIN, user.getUsername(), user.getEmail(), null, user.getUserId());
    System.out.println("admin::" + admin.getEmail() + admin.getUserPasswd() + admin.getUsername() + admin.getId() + admin.getUserrole());
    return (AdminUserDto)admin;
    }
    else{
        return new AdminUserDto();
    }
    }
    
    
}

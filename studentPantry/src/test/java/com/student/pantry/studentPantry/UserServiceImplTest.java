package com.student.pantry.studentPantry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.dto.UserRole;
import com.student.pantry.studentPantry.entity.PantryUser;
import com.student.pantry.studentPantry.repository.AdminUserJpa;
import com.student.pantry.studentPantry.repository.PantryUserRepository;
import com.student.pantry.studentPantry.response.UserResponse;
import com.student.pantry.studentPantry.service.AdminLoginManager;
import com.student.pantry.studentPantry.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private PantryUserRepository pantryUserRepository;

    @Mock
    private AdminUserJpa adminUserJpa;

    @Mock
    private AdminLoginManager adminLoginManager;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegisterUser_Success() {
        // Arrange
        StudentUserDto userDto = new StudentUserDto();
        userDto.setUserrole(UserRole.STUDENT);

        //when(pantryUserRepository.findByEmail(anyString())).thenReturn(null);
        when(pantryUserRepository.save(any(PantryUser.class))).thenReturn(new PantryUser());

        // Act
        UserResponse response = userService.registerUser(userDto);

        // Assert
        assertEquals("Registration Successfull", response.getMessage());
    }

    @Test
    void testRegisterUser_DuplicateRegistration() {
        // Arrange
        StudentUserDto userDto = new StudentUserDto();
        userDto.setUserrole(UserRole.STUDENT);
        userDto.setEmail("test@test.com");

        when(pantryUserRepository.findByEmail(anyString())).thenReturn(new PantryUser());

        // Act
        UserResponse response = userService.registerUser(userDto);

        // Assert
        assertEquals("duplicate registration", response.getMessage());
    }

   @Test
    void testLogin_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUserrole(UserRole.STUDENT);
        userDto.setEmail("test@example.com");
        userDto.setUserPasswd("password");

        PantryUser pantryUser = new PantryUser();
        pantryUser.setEmail("test@example.com");
        pantryUser.setUserrole(com.student.pantry.studentPantry.entity.UserRole.STUDENT);
        pantryUser.setUserId(1234L);
        pantryUser.setUsername("username");

        when(adminLoginManager.login(userDto)).thenReturn(true);
        when(pantryUserRepository.findByEmailAndUserPasswd(anyString(), anyString())).thenReturn(pantryUser);
        // Act
        UserResponse response = userService.login(userDto);

        // Assert
        assertEquals("LOGIN SUCESS", response.getMessage());
    }

    @Test
    void testLogin_AdminLoginNotAllowed() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUserrole(UserRole.ADMIN);
        userDto.setEmail("admin@example.com");
        userDto.setUserPasswd("adminPassword");

        when(adminLoginManager.login(userDto)).thenReturn(false);

        // Act
        UserResponse response = userService.login(userDto);

        // Assert
        assertEquals("Only one admin login allowed at a time ", response.getMessage());
    }

    
}

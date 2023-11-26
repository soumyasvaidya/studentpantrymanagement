package com.student.pantry.studentPantry.factory;

import org.springframework.stereotype.Component;

import com.student.pantry.studentPantry.dto.AdminUserDto;
import com.student.pantry.studentPantry.dto.StudentUserDto;
import com.student.pantry.studentPantry.dto.UserDto;
import com.student.pantry.studentPantry.dto.UserRole;

@Component
public class UserDtoFactory {
    public static UserDto createUserDTO(UserRole role, String username, String email, String password, Long roleId) {
        if (role == UserRole.STUDENT) {
            return createStudentDTO(username, email, password, roleId);
        } else if (role == UserRole.ADMIN) {
            return createAdminDTO(username, email, password, roleId);
        } else {
            return createUserDTO(username, email, password, roleId);
        }
    }

    public static UserDto createUserDTO(String username, String email, String password, Long id) {
        UserDto userDTO = new UserDto();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setUserPasswd(password);
        return userDTO;
    }

    public static StudentUserDto createStudentDTO(String username, String email, String password, Long studentId) {
        StudentUserDto studentDTO = new StudentUserDto();
        studentDTO.setId(studentId);
        studentDTO.setUsername(username);
        studentDTO.setEmail(email);
        studentDTO.setUserPasswd(password);
        studentDTO.setStudentId(studentId);
        studentDTO.setUserrole(UserRole.STUDENT);
        return studentDTO;
    }

    public static AdminUserDto createAdminDTO(String username, String email, String password, Long adminId) {
        AdminUserDto adminDTO = new AdminUserDto();
        adminDTO.setId(adminId);
        adminDTO.setUsername(username);
        adminDTO.setEmail(email);
        adminDTO.setUserPasswd(password);
        adminDTO.setAdminId(adminId);
        adminDTO.setUserrole(UserRole.ADMIN);
        return adminDTO;
    }
}

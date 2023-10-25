package com.student.pantry.studentPantry.factory;

import com.student.pantry.studentPantry.dto.UserRole;
import com.student.pantry.studentPantry.entity.AdminUser;
import com.student.pantry.studentPantry.entity.StudentUser;
import com.student.pantry.studentPantry.entity.User;

public class UserEntityFactory {
    public static User createUser(UserRole role, String username, String email, String password, Long roleId) {
        if (role == UserRole.STUDENT) {
            StudentUser student = new StudentUser();
            student.setUsername(username);
            student.setEmail(email);
            student.setPassword(password);
            student.setStudentId(roleId);
            return student;
        } else if (role == UserRole.ADMIN) {
            AdminUser admin = new AdminUser();
            admin.setUsername(username);
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setAdminId(roleId);
            return admin;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            return user;
        }
    }
}

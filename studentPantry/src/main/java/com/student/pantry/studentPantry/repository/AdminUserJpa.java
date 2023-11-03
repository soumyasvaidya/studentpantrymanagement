package com.student.pantry.studentPantry.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.student.pantry.studentPantry.entity.AdminUser;

@Repository
public interface AdminUserJpa extends CrudRepository<AdminUser, Long> {

    AdminUser findByEmail(String lastName);

    AdminUser findById(long id);

    AdminUser findByEmailAndUserPasswd(String email, String password);
}

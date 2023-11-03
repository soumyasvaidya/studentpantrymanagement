package com.student.pantry.studentPantry.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.student.pantry.studentPantry.entity.User;


@Repository
public interface UserJpa extends CrudRepository<User, Long> {

    User findByEmail(String lastName);

    User findById(long id);

    User findByEmailAndUserPasswd(String email, String password);
}


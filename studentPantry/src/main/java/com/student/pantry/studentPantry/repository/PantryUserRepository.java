package com.student.pantry.studentPantry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.pantry.studentPantry.entity.PantryUser;


@Repository
public interface PantryUserRepository extends JpaRepository<PantryUser, Long> {

    PantryUser findByEmail(String lastName);

    PantryUser findById(long id);

    PantryUser findByEmailAndUserPasswd(String email, String password);
    
    List<PantryUser> findAll();
}

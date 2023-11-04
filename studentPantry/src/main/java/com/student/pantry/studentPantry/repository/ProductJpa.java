package com.student.pantry.studentPantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.pantry.studentPantry.entity.Products;

public interface ProductJpa extends JpaRepository<Products, Long>{

}

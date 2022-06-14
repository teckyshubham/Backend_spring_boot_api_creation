package com.techyShubham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techyShubham.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

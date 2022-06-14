package com.techyShubham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techyShubham.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}

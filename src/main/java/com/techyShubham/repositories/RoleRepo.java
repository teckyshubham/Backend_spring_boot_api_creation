package com.techyShubham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techyShubham.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {

	

}

package com.techyShubham.services;

import com.techyShubham.payloads.UserDto;

//import antlr.collections.List;
import java.util.*;

import org.springframework.stereotype.Service;
@Service
public interface UserService {

		UserDto createUser(UserDto user);
		UserDto updateUser(UserDto user,Integer userId);
//		UserDto getUserById(UserDto user);
		List<UserDto> getAllUsers();
		void deleteUser(Integer userId);
		UserDto getUserById(Integer userId);
	
}

package com.techyShubham.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techyShubham.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@Getter
@Setter
@Getter
public class UserDto {

	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username must be min 4 Char")
	private String name;
	@Email(message="Email adress not valid")
	private String email;
	
	
	
	@NotEmpty
	@Size(min=3 , max=10,message="Passwrd must be min 3 Char")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}

package com.techyShubham.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techyShubham.payloads.ApiResponse;
import com.techyShubham.payloads.CategoryDto;
//import com.techyShubham.payloads.UserDto;
import com.techyShubham.services.CategoryService;
//import com.techyShubham.services.UserService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	public CategoryService categoryService;

	
	//post
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
	}
	
	
	///update put
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto userDto,@PathVariable("catId") Integer cid){
		CategoryDto updatedCategory=this.categoryService.updateCategory(userDto,cid);
		return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer cid){
		this.categoryService.deleteCategory(cid);
//		UserDto updatedUser=this.userService.updateUser(uid);
		return new ResponseEntity<>(new ApiResponse("Category Deleted Succesfully",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/")
	 public ResponseEntity<List<CategoryDto>> getAllCategory(){
		 return ResponseEntity.ok(this.categoryService.getCategories());
	 }
	
	@GetMapping("/{catId}")
	 public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer cid){
		 return ResponseEntity.ok(this.categoryService.getCategory(cid));
	 }

}

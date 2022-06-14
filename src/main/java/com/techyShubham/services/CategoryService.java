package com.techyShubham.services;
import java.util.List;
import com.techyShubham.payloads.CategoryDto;
import java.util.*;
public interface CategoryService {
	
	
	//create 
	CategoryDto createCategory(CategoryDto categoryDto );
	//delet
	void deleteCategory(Integer categoryId);
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId );
	//get
	CategoryDto getCategory(Integer CategoryId);
	//getall
	
	List<CategoryDto> getCategories();

}

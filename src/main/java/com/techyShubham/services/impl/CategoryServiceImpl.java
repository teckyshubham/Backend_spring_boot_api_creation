package com.techyShubham.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techyShubham.entities.Category;
import com.techyShubham.exceptions.ResourceNotFoundException;
import com.techyShubham.payloads.CategoryDto;
import com.techyShubham.repositories.CategoryRepo;
import com.techyShubham.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepo categoryRepo;

	public CategoryServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		
		
		// TODO Auto-generated method stub
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		// TODO Auto-generated method stub
		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat=this.categoryRepo.save(cat);
		// TODO Auto-generated method stub
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer CategoryId) {
		Category cat=this.categoryRepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",CategoryId));
		
		// TODO Auto-generated method stub
		return this.modelMapper.map(cat, CategoryDto.class);
	}
	

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		// TODO Auto-generated method stub
		return catDtos;
	}

}


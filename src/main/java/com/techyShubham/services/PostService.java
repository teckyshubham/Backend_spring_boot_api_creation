package com.techyShubham.services;

import java.util.*;

//import com.techyShubham.entities.Post;
import com.techyShubham.payloads.PostDto;
import com.techyShubham.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
//	
//	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List <PostDto> getPostsByCategory(Integer categoryId);
	
	
	List <PostDto> getPostsByUser(Integer userId);
	
	List <PostDto> searchPosts(String keyword);
}

package com.techyShubham.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techyShubham.entities.Category;
import com.techyShubham.entities.Post;
import com.techyShubham.entities.User;
import com.techyShubham.exceptions.ResourceNotFoundException;
import com.techyShubham.payloads.PostDto;
import com.techyShubham.payloads.PostResponse;
import com.techyShubham.repositories.CategoryRepo;
import com.techyShubham.repositories.PostRepo;
import com.techyShubham.repositories.UserRepo;
import com.techyShubham.services.PostService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Service
@Getter
@Setter
@NoArgsConstructor
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id  ",userId));
		
		Category category =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category ","CategoryId ",categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
//		post.setAddedDate(new Date());
		post.setAddedDate(new Date());
		
		post.setImageName("default.png");
		post.setCategory(category);
		post.setUser(user)  ;
		
		Post newPost=this.postRepo.save(post);
		
		
		
		// TODO Auto-generated method stub
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post ","PostId ",postId));
		post.setTitle(postDto.getTitle());
		post.setImageName(postDto.getImageName());
		post.setContent(postDto.getContent());
		
		Post updatedPost =this.postRepo.save(post);
		
		// TODO Auto-generated method stub
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> allPosts = pagePost.getContent();

		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
//		postResponse.setTotalElements(pagePost.getTotalElements());

		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id  ",categoryId));
		
		List<Post> posts=this.postRepo.findByCategory(cat);
		List <PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		
		
		
		// TODO Auto-generated method stub
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user Id  ",userId));
		
		List<Post> posts=this.postRepo.findByUser(user);
		List <PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		
		
		
		// TODO Auto-generated method stub
		return postDtos;
		// TODO Auto-generated method stub
//		return null;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	

}

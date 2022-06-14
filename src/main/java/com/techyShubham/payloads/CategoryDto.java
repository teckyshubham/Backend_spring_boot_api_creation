package com.techyShubham.payloads;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private Integer id;
	
//	@Column(name="title",nullable=false,length=100)
	private String categoryTitle;
//	@Column(name="description")
	private String categoryDescription;

}

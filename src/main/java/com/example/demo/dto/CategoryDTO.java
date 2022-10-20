package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String name;


    public CategoryDTO() {}

    public CategoryDTO(Category cat) {
        this.id = cat.getId();
        this.name = cat.getName();
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    
}

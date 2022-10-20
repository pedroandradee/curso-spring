package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.demo.domain.Category;



public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho precisa ser entre 5 e 80 caracteres!")
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

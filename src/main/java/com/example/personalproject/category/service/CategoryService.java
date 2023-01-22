package com.example.personalproject.category.service;


import com.example.personalproject.category.model.CategoryInput;
import com.example.personalproject.category.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    
    List<CategoryDto> list();

    boolean add(String categoryName);

    boolean update(CategoryInput parameter);

    boolean del(long id);
    
    List<CategoryDto> frontList(CategoryDto parameter);
    
    

}

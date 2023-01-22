package com.example.personalproject.category.service;

import com.example.personalproject.category.mapper.CategoryMapper;
import com.example.personalproject.category.model.CategoryInput;
import com.example.personalproject.category.repository.CategoryRepository;
import com.example.personalproject.category.dto.CategoryDto;
import com.example.personalproject.category.entity.Category;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }
    
    @Override
    public List<CategoryDto> list() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryDto.of(categories);
    }
    
    @Override
    public boolean add(String categoryName) {
        
        Category category = Category.builder()
                .categoryName(categoryName)
                .usingYn(true)
                .build();
        categoryRepository.save(category);
        
        return true;
    }
    
    @Override
    public boolean update(CategoryInput parameter) {
        
        Optional<Category> optionalCategory = categoryRepository.findById(parameter.getId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(parameter.getCategoryName());
            category.setUsingYn(parameter.isUsingYn());
            categoryRepository.save(category);
        }
        
        return true;
    }
    
    @Override
    public boolean del(long id) {
        
        categoryRepository.deleteById(id);
        
        return true;
    }
    
    @Override
    public List<CategoryDto> frontList(CategoryDto parameter) {

        return categoryMapper.select(parameter);
    }
}

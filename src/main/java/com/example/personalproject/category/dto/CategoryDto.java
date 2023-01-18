package com.example.personalproject.category.dto;

import com.example.personalproject.category.entity.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

	Long id;
	String categoryName;
	boolean usingYn;

	int productCount;

	public static List<CategoryDto> of(List<Category> categories) {
		if (categories != null) {
			List<CategoryDto> categoryList = new ArrayList<>();
			for (Category x : categories) {
				categoryList.add(of(x));
			}
			return categoryList;
		}

		return null;
	}

	public static CategoryDto of(Category category) {
		return CategoryDto.builder()
			.id(category.getId())
			.categoryName(category.getCategoryName())
			.usingYn(category.isUsingYn())
			.build();
	}

}

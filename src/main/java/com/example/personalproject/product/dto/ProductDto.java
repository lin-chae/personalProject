package com.example.personalproject.product.dto;

import com.example.personalproject.product.entity.Product;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {
	Long productId;
	long categoryId;
	String imagePath;
	String keyword;
	String productName;
	String summary;
	String contents;
	long price;
	long productQuantity;
	LocalDateTime regDt;
	LocalDateTime udtDt;

	String filename;
	String urlFilename;

	//추가컬럼
	long totalCount;
	long seq;

	public static ProductDto of(Product product) {
		return ProductDto.builder()
			.categoryId(product.getCategoryId())
			.imagePath(product.getImagePath())
			.keyword(product.getKeyword())
			.productName(product.getProductName())
			.summary(product.getSummary())
			.contents(product.getContents())
			.price(product.getPrice())
			.productQuantity(product.getProductQuantity())
			.regDt(product.getRegDt())
			.udtDt(product.getUdtDt())
			.filename(product.getFilename())
			.urlFilename(product.getUrlFilename())
			.build();
	}

	public static List<ProductDto> of(List<Product> productDtoList) {

		if (productDtoList == null) {
			return null;
		}

		List<ProductDto> productList = new ArrayList<>();
		for (Product x : productDtoList) {
            productList.add(ProductDto.of(x));
		}
		return productList;
	}

}


















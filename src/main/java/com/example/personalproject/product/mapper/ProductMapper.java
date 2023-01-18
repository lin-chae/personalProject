package com.example.personalproject.product.mapper;

import com.example.personalproject.product.dto.ProductDto;
import com.example.personalproject.product.model.ProductParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    long selectListCount(ProductParam parameter);
    List<ProductDto> selectList(ProductParam parameter);
    
}

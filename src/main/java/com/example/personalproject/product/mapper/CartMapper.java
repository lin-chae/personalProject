package com.example.personalproject.product.mapper;

import com.example.personalproject.product.dto.CartDto;
import com.example.personalproject.product.model.CartParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
    
    long selectListCount(CartParam parameter);
    List<CartDto> selectList(CartParam parameter);
    
    List<CartDto> selectListMyProduct(CartParam parameter);
}

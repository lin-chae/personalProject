package com.example.personalproject.product.service;

import com.example.personalproject.product.dto.ProductDto;
import com.example.personalproject.product.model.ProductInput;
import com.example.personalproject.product.model.ProductParam;
import java.util.List;

public interface ProductService {

    boolean add(ProductInput parameter);
    

    boolean set(ProductInput parameter);
    

    List<ProductDto> list(ProductParam parameter);
    

    ProductDto getById(long id);
    

    boolean del(String idList);
    

    List<ProductDto> frontList(ProductParam parameter);

    ProductDto frontDetail(long id);

    
    /**
     * 전체 강좌 목록
     */
    List<ProductDto> listAll();
    
}

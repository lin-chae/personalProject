package com.example.personalproject.product.service;

import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.product.dto.ProductDto;
import com.example.personalproject.product.model.ProductInput;
import com.example.personalproject.product.model.ProductParam;
import com.example.personalproject.product.model.CartInput;
import java.util.List;

public interface ProductService {

    boolean add(ProductInput parameter);
    

    boolean set(ProductInput parameter);
    

    List<ProductDto> list(ProductParam parameter);
    

    ProductDto getById(long id);
    

    boolean del(String idList);
    

    List<ProductDto> frontList(ProductParam parameter);

    ProductDto frontDetail(long id);

    

    List<ProductDto> listAll();

    ServiceResult req(CartInput parameter);
}

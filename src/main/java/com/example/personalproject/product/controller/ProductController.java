package com.example.personalproject.product.controller;


import com.example.personalproject.admin.controller.BaseController;
import com.example.personalproject.category.dto.CategoryDto;
import com.example.personalproject.category.service.CategoryService;
import com.example.personalproject.product.dto.ProductDto;
import com.example.personalproject.product.model.ProductParam;
import com.example.personalproject.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProductController extends BaseController {
    
    private final ProductService productService;
    private final CategoryService categoryService;
    
    @GetMapping("/product")
    public String product(Model model
            , ProductParam parameter) {
        
        List<ProductDto> list = productService.frontList(parameter);
        model.addAttribute("list", list);
        
        int productTotalCount = 0;
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (categoryList != null) {
            for(CategoryDto x : categoryList) {
                productTotalCount += x.getProductCount();
            }
        }
        
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productTotalCount", productTotalCount);
        
        return "product/index";
    }
    
    @GetMapping("/product/{id}")
    public String productDetail(Model model
            , ProductParam parameter) {
        
        ProductDto detail = productService.frontDetail(parameter.getProductId());
        model.addAttribute("detail", detail);
        
        return "product/detail";
    }
    
    
    
    
    
    
}

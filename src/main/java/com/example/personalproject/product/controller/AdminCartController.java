package com.example.personalproject.product.controller;


import com.example.personalproject.admin.controller.BaseController;
import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.product.dto.CartDto;
import com.example.personalproject.product.dto.ProductDto;
import com.example.personalproject.product.model.CartParam;
import com.example.personalproject.product.service.CartService;
import com.example.personalproject.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/cart")
public class AdminCartController extends BaseController {
    
    private final ProductService productService;
    private final CartService cartService;
    
    @GetMapping("/list.do")
    public String list(Model model, CartParam parameter
        , BindingResult bindingResult) {
    
        parameter.init();
        List<CartDto> list = cartService.list(parameter);
        
        long totalCount = 0;
        if (!CollectionUtils.isEmpty(list)) {
            totalCount = list.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
    
        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        
        
        List<ProductDto> productList = productService.listAll();
        model.addAttribute("productList", productList);
        
        
    
        return "admin/cart/list";
    }
    
    @PostMapping("/status.do")
    public String status(Model model, CartParam parameter) {
        
        ServiceResult result = cartService.updateStatus(parameter.getCartId(), parameter.getOrderStatus());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }
        
        return "redirect:/admin/cart/list.do";
    }
}

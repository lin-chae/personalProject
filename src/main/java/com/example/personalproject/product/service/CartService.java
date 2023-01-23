package com.example.personalproject.product.service;

import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.product.dto.CartDto;
import com.example.personalproject.product.model.CartParam;
import com.example.personalproject.product.model.OrderStatus;
import java.util.List;

public interface CartService {

    List<CartDto> list(CartParam parameter);

    CartDto detail(long orderId);

    ServiceResult updateStatus(long cartId, OrderStatus orderStatus);


    ServiceResult cancel(long id);
}
package com.example.personalproject.product.dto;

import com.example.personalproject.product.entity.Cart;
import com.example.personalproject.product.entity.Product;
import com.example.personalproject.product.model.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartDto {
    
    long cartId;
    long productId;
    String email;
    
    long payPrice;
    OrderStatus orderStatus;
    
    LocalDateTime regDt;
    
    
    
    // JOIN
    String userName;
    String phoneNumber;
    String subject;
    
    
    
    //추가컬럼
    long totalCount;
    long seq;
    
    public static CartDto of(Cart x) {
    
        return CartDto.builder()
                .cartId(x.getCartId())
                .productId(x.getProductId())
                .email(x.getEmail())
                .payPrice(x.getPayPrice())
                .orderStatus(x.getOrderStatus())
                .regDt(x.getRegDt())
                .build();
    }
    
    
    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    
    }
    
}

















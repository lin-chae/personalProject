package com.example.personalproject.product.model;
import com.example.personalproject.admin.model.CommonParam;
import com.example.personalproject.member.entity.Member;
import lombok.Data;

@Data
public class CartParam extends CommonParam {

    long cartId;
    OrderStatus orderStatus;
    
    Member user;
    
    
    long searchProductId;
}

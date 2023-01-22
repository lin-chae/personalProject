package com.example.personalproject.product.model;
import com.example.personalproject.admin.model.CommonParam;
import lombok.Data;

@Data
public class CartParam extends CommonParam {

    long cartId;
    OrderStatus orderStatus;
    
    long userId;
    
    
    long searchProductId;
}

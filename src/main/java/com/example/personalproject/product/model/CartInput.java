package com.example.personalproject.product.model;

import lombok.Data;

@Data
public class CartInput {

    long productId;
    long userId;

    long selectedQuantity;
    long cartId;
}

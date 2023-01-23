package com.example.personalproject.product.model;

import lombok.Data;

@Data
public class ProductInput {
    long productId;
    long categoryId;
    String productName;
    String keyword;
    String summary;
    String contents;
    long price;
    long quantity;
    
    //삭제를 위한
    String idList;
    
    
    //ADD
    String filename;
    String urlFilename;

}

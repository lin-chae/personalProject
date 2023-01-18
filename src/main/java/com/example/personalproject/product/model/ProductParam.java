package com.example.personalproject.product.model;

import com.example.personalproject.admin.model.CommonParam;
import lombok.Data;

@Data
public class ProductParam extends CommonParam {

    long id;
    long categoryId;

}

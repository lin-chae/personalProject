package com.example.personalproject.product.entity;

import com.example.personalproject.product.model.OrderStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cartId;
    
    long productId;
    String email;
    
    long payPrice;
    OrderStatus orderStatus;
    
    LocalDateTime regDt;
}

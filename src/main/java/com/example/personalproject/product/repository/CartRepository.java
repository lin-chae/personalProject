package com.example.personalproject.product.repository;


import com.example.personalproject.product.entity.Cart;
import com.example.personalproject.product.model.OrderStatus;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    long countByProductIdAndEmailAndOrderStatusIn(long productId, String email, Collection<OrderStatus> orderStatus);
    
}

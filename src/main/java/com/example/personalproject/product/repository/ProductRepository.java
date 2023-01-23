package com.example.personalproject.product.repository;


import com.example.personalproject.product.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<List<Product>> findByCategoryId(long categoryId);
}

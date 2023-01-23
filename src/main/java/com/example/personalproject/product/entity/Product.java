package com.example.personalproject.product.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private long categoryId;

    private String imagePath;
    private String keyword;
    private String productName;
    
    @Column(length = 1000)
    private String summary;
    
    @Lob
    private String contents;
    private long price;
    private long productQuantity;

    private LocalDateTime regDt;//등록일(추가날짜)
    private LocalDateTime udtDt;//수정일(수정날짜)


    private String filename;
    private String urlFilename;
    
}

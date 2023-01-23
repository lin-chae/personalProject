package com.example.personalproject.product.entity;

import com.example.personalproject.member.entity.Member;
import com.example.personalproject.product.model.OrderStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	private Long cartId;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Member user;

	private long payPrice;
	private long selectedQuantity;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	private LocalDateTime regDt;

	public long getProductId() {
		return product.getProductId();
	}
	public long getUserId() {
		return user.getUserId();
	}
	public String getProductName(){
		return product.getProductName();
	}
}

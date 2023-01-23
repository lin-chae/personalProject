package com.example.personalproject.product.dto;

import com.example.personalproject.product.entity.Cart;
import com.example.personalproject.product.model.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartDto {

	long cartId;
	long productId;
	long userId;
	long payPrice;
	OrderStatus orderStatus;

	LocalDateTime regDt;

	long selectedQuantity;
	// JOIN
	String userName;
	String phoneNumber;
	String subject;
	String productName;


	//추가컬럼
	long totalCount;
	long seq;

	public static CartDto of(Cart cart) {
		return CartDto.builder()
			.cartId(cart.getCartId())
			.productId(cart.getProductId())
			.productName(cart.getProductName())
			.userId(cart.getUserId())
			.payPrice(cart.getPayPrice())
			.orderStatus(cart.getOrderStatus())
			.selectedQuantity(cart.getSelectedQuantity())
			.regDt(cart.getRegDt())
			.build();
	}


	public String getRegDtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		return regDt != null ? regDt.format(formatter) : "";

	}

}

















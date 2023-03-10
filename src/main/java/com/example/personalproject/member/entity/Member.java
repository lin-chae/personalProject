package com.example.personalproject.member.entity;

import com.example.personalproject.member.model.UserStatus;
import com.example.personalproject.product.entity.Cart;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private boolean emailVerified;
	private LocalDateTime registrationDate;
	private LocalDateTime updateDate;
	private boolean adminVerified;
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	private String emailAuthenticationKey;
	private String resetPasswordKey;
	private LocalDateTime resetPasswordLimitDt;

	@OneToMany(mappedBy = "user")
	private List<Cart> cartList;
}

package com.example.personalproject.product.controller;

import com.example.personalproject.admin.controller.BaseController;
import com.example.personalproject.category.service.CategoryService;
import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.member.entity.Member;
import com.example.personalproject.member.repository.MemberRepository;
import com.example.personalproject.model.ResponseResult;
import com.example.personalproject.product.model.CartInput;
import com.example.personalproject.product.service.ProductService;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiProductController extends BaseController {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final MemberRepository memberRepository;

	@PostMapping("/api/product/req.api")
	public ResponseEntity<?> productReq(Model model,
		 CartInput parameter,
		Principal principal) {

		Optional<Member> member = memberRepository.findByEmail(principal.getName());
		member.ifPresent(value -> parameter.setUserId(value.getUserId()));
		ServiceResult result = productService.req(parameter);
		if (!result.isResult()) {
			ResponseResult responseResult = new ResponseResult(false, result.getMessage());
			return ResponseEntity.ok().body(responseResult);
		}

		ResponseResult responseResult = new ResponseResult(true);
		return ResponseEntity.ok().body(responseResult);
	}


}

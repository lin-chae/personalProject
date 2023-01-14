package com.example.personalproject.member.controller;

import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.member.model.MemberInput;
import com.example.personalproject.member.service.MemberService;
import com.example.personalproject.model.dto.MemberDto;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@RequestMapping("/login")
	public String login() {

		return "member/login";
	}

	@GetMapping("/register")
	public String register() {

		return "member/register";
	}

	@PostMapping("/register")
	public String registerSubmit(Model model, HttpServletRequest request
		, MemberInput parameter) {

		boolean result = memberService.register(parameter);
		model.addAttribute("result", result);

		return "member/register_complete";
	}

	@GetMapping("/email-auth")
	public String emailAuth(Model model, HttpServletRequest request) {

		String uuid = request.getParameter("id");
		System.out.println(uuid);

		boolean result = memberService.emailAuth(uuid);
		model.addAttribute("result", result);

		return "member/email_auth";
	}

	@GetMapping("/info")
	public String memberInfo(Model model, Principal principal) {
		if(principal==null){
			return "error/denied";
		}
		String userEmail = principal.getName();
		MemberDto detail = memberService.detail(userEmail);
		model.addAttribute("detail", detail);

		return "member/info";
	}

	@PostMapping("/info")
	public String memberInfoSubmit(Model model
		, MemberInput parameter
		, Principal principal) {

		String email = principal.getName();
		parameter.setEmail(email);

		ServiceResult result = memberService.updateMember(parameter);
		if (!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "common/error";
		}
		return "redirect:/member/info";
	}
}

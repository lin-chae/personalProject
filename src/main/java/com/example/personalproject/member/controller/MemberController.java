package com.example.personalproject.member.controller;

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
public class MemberController {

	private final MemberService memberService;

	@RequestMapping("/member/login")
	public String login() {

		return "member/login";
	}

	@GetMapping("/member/register")
	public String register() {

		return "member/register";
	}

	@PostMapping("/member/register")
	public String registerSubmit(Model model, HttpServletRequest request
		, MemberInput parameter) {

		boolean result = memberService.register(parameter);
		model.addAttribute("result", result);

		return "member/register_complete";
	}

	@GetMapping("/member/email-auth")
	public String emailAuth(Model model, HttpServletRequest request) {

		String uuid = request.getParameter("id");
		System.out.println(uuid);

		boolean result = memberService.emailAuth(uuid);
		model.addAttribute("result", result);

		return "member/email_auth";
	}

	@GetMapping("/member/info")
	public String memberInfo(Model model, Principal principal) {

		String userEmail = principal.getName();
		MemberDto detail = memberService.detail(userEmail);

		model.addAttribute("detail", detail);

		return "member/info";
	}
}

package com.example.personalproject.member.controller;

import com.example.personalproject.admin.controller.BaseController;
import com.example.personalproject.admin.model.MemberInput;
import com.example.personalproject.admin.model.MemberParam;
import com.example.personalproject.member.service.MemberService;
import com.example.personalproject.member.dto.MemberDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController extends BaseController {

	private final MemberService memberService;

	@GetMapping("/list.do")
	public String list(Model model, MemberParam parameter) {

		parameter.init();
		List<MemberDto> members = memberService.list(parameter);

		long totalCount = 0;
		if (members != null && members.size() > 0) {
			totalCount = members.get(0).getTotalCount();
		}
		String queryString = parameter.getQueryString();
		String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(),
			parameter.getPageIndex(), queryString);

		model.addAttribute("list", members);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pagerHtml);

		return "admin/member/list";
	}

	@GetMapping("/detail.do")
	public String detail(Model model, MemberParam parameter) {

		parameter.init();
		MemberDto member = memberService.detail(parameter.getEmail());
		model.addAttribute("member", member);

		return "admin/member/detail";
	}

	@PostMapping("/status.do")
	public String status(Model model, MemberInput parameter) {

		boolean result = memberService.updateStatus(parameter.getEmail(), parameter.getUserStatus());

		return "redirect:/admin/member/detail.do?email=" + parameter.getEmail();
	}
	@PostMapping("/password.do")
	public String password(Model model, MemberInput parameter) {


		boolean result = memberService.updatePassword(parameter.getEmail(), parameter.getPassword());

		return "redirect:/admin/member/detail.do?email=" + parameter.getEmail();
	}

}

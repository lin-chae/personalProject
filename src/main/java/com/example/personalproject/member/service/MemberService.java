package com.example.personalproject.member.service;


import com.example.personalproject.MemberDto;
import com.example.personalproject.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService {

	boolean register(MemberInput parameter);

	/**
	 * uuid에 해당하는 계정을 활성화 함.
	 */
	boolean emailAuth(String uuid);

	MemberDto detail(String email);

	boolean resetPassword(String email, String password);

	String login(MemberInput parameter);
}

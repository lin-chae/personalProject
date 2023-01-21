package com.example.personalproject.member.service;


import com.example.personalproject.admin.model.MemberParam;
import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.member.model.MemberInput;
import com.example.personalproject.member.model.ResetPasswordInput;
import com.example.personalproject.member.model.UserStatus;
import com.example.personalproject.member.dto.MemberDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

	boolean register(MemberInput parameter);

	/**
	 * uuid에 해당하는 계정을 활성화 함.
	 */
	boolean emailAuth(String uuid);

	MemberDto detail(String email);

	ServiceResult updateMember(MemberInput parameter);

	ServiceResult updateMemberPassword(MemberInput parameter);

	boolean updateStatus(String email, UserStatus userStatus);

	List<MemberDto> list(MemberParam parameter);

	boolean checkResetPassword(String uuid);

	boolean resetPassword(String email, String password);

	boolean sendResetPassword(ResetPasswordInput parameter);

	ServiceResult withdraw(String email, String password);

	boolean updatePassword(String email, String password);
}

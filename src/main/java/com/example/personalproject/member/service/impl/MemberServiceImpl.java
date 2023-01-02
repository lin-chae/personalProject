package com.example.personalproject.member.service.impl;

import com.example.personalproject.MemberDto;
import com.example.personalproject.components.MailComponents;
import com.example.personalproject.member.entity.Member;
import com.example.personalproject.member.exception.MemberNotEmailAuthException;
import com.example.personalproject.member.exception.MemberStopUserException;
import com.example.personalproject.member.model.MemberInput;
import com.example.personalproject.member.model.UserStatus;
import com.example.personalproject.member.repository.MemberRepository;
import com.example.personalproject.member.service.MemberService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final MailComponents mailComponents;

	/**
	 * 회원 가입
	 */
	@Override
	public boolean register(MemberInput parameter) {

		Optional<Member> optionalMember = memberRepository.findByEmail(parameter.getEmail());
		if (optionalMember.isPresent()) {
			//현재 userId에 해당하는 데이터 존재
			return false;
		}

		String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
		String uuid = UUID.randomUUID().toString();

		Member member = Member.builder()
			.email(parameter.getEmail())
			.name(parameter.getName())
			.phoneNumber(parameter.getPhoneNumber())
			.password(encPassword)
			.registrationDate(LocalDateTime.now())
			.emailVerified(false)
			.userStatus(UserStatus.REQ)
			.adminVerified(false)
			.emailAuthenticationKey(uuid)
			.build();
		memberRepository.save(member);
		String email = parameter.getEmail();
		String subject = "사이트 가입을 축하드립니다. ";
		String text = "<p>사이트 가입을 축하드립니다.<p><p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
			+ "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid
			+ "'> 가입 완료 </a></div>";
		mailComponents.sendMail(email, subject, text);

		return true;
	}

	@Override
	public boolean emailAuth(String uuid) {

		Optional<Member> optionalMember = memberRepository.findByEmailAuthenticationKey(uuid);
		if (!optionalMember.isPresent()) {
			return false;
		}

		Member member = optionalMember.get();

		if (member.isEmailVerified()) {
			return false;
		}

		member.setUserStatus(UserStatus.ING);
		member.setEmailVerified(true);
		member.setRegistrationDate(LocalDateTime.now());
		memberRepository.save(member);

		return true;
	}

	@Override
	public MemberDto detail(String email) {

		Optional<Member> optionalMember = memberRepository.findByEmail(email);
		if (!optionalMember.isPresent()) {
			return null;
		}

		Member member = optionalMember.get();

		return MemberDto.of(member);
	}

	@Override
	public boolean resetPassword(String email, String password) {
		return false;
	}

	@Override
	public String login(MemberInput parameter) {
		Optional<Member> optionalMember = memberRepository.findByEmail(parameter.getEmail());
		if(optionalMember.isEmpty()){
			return "회원 정보가 존재하지 않습니다.";
		}
		Member member = optionalMember.get();
		switch (member.getUserStatus()) {
			case REQ : return "이메일 활성화 이후에 로그인을 해주세요.";
			case STOP : return "정지된 회원 입니다.";
			case WITHDRAW : return "탈퇴된 회원 입니다.";
		}
		if(!BCrypt.checkpw(parameter.getPassword(),member.getPassword())){
			return "로그인 실패";
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (member.isAdminVerified()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		memberRepository.save(member);
		return " ";
	}
}

package com.example.personalproject.model.dto;


import com.example.personalproject.member.entity.Member;
import com.example.personalproject.member.model.UserStatus;
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
public class MemberDto {

	Long userId;
	String email;
	String name;
	String password;
	String phoneNumber;
	boolean emailVerified;
	LocalDateTime registrationDate;
	LocalDateTime updateDate;//회원정보 수정일
	boolean adminVerified;
	UserStatus userStatus;
	String emailAuthenticationKey;
	long totalCount;


	public static MemberDto of(Member member) {
		return MemberDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.phoneNumber(member.getPhoneNumber())
			.password(member.getPassword())
			.registrationDate(member.getRegistrationDate())
			.updateDate(member.getUpdateDate())
			.emailVerified(member.isEmailVerified())
			.emailAuthenticationKey(member.getEmailAuthenticationKey())
			.adminVerified(member.isAdminVerified())
			.userStatus(member.getUserStatus())
			.build();
	}

	public String getRegDtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return registrationDate != null ? registrationDate.format(formatter) : "";
	}

	public String getUdtDtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return updateDate != null ? updateDate.format(formatter) : "";
	}

}

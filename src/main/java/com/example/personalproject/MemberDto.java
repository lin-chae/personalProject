package com.example.personalproject;


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

	Long user_Id;
	String email;
	String name;
	String password;
	String phoneNumber;
	boolean emailVerified;
	LocalDateTime registration_date;
	LocalDateTime update_date;//회원정보 수정일
	boolean adminVerified;
	UserStatus user_status;
	String emailAuthenticationKey;


	public static MemberDto of(Member member) {

		return MemberDto.builder()
			.user_Id(member.getUserId())
			.name(member.getName())
			.phoneNumber(member.getPhoneNumber())
			.password(member.getPassword())
			.registration_date(member.getRegistrationDate())
			.update_date(member.getUpdateDate())
			.emailVerified(member.isEmailVerified())
			.emailAuthenticationKey(member.getEmailAuthenticationKey())
			.adminVerified(member.isAdminVerified())
			.user_status(member.getUserStatus())
			.build();
	}

	public String getRegDtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return registration_date != null ? registration_date.format(formatter) : "";
	}

	public String getUdtDtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return update_date != null ? update_date.format(formatter) : "";
	}

}

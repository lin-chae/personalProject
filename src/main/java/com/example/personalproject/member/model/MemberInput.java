package com.example.personalproject.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {

	private String email;
	private String name;
	private String phoneNumber;
	private String password;

	private String newPassword;

}

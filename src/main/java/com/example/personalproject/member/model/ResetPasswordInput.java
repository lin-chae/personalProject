package com.example.personalproject.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ResetPasswordInput {

	private String email;

	private String id;
	private String password;
}

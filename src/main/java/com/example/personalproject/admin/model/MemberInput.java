package com.example.personalproject.admin.model;

import com.example.personalproject.member.model.UserStatus;
import lombok.Data;

@Data
public class MemberInput {

	String email;
	UserStatus userStatus;
	String password;
}

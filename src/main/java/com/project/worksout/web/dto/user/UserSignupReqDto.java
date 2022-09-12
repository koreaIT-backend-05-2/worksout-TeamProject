package com.project.worksout.web.dto.user;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.worksout.domain.user.User;

import lombok.Data;

@Data
public class UserSignupReqDto {
	private String name;
	private int gender;
	private String birth;
	private String username;
	private String password;
	private String phone;
	private boolean qualificationAgree;
	private boolean infoAgree;
	private boolean emailAgree;
	private boolean smsAgree;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	public User toEntity() {
		return User.builder()
					.user_name(name)
					.user_gender(gender)
					.user_birth(birth)
					.user_id(username)
					.user_password(new BCryptPasswordEncoder().encode(password))
					.user_phone(phone)
					.user_roles("ROLE_ADMIN")
					.user_level("BASIC")
					.user_qualification_agreement(qualificationAgree)
					.user_info_agreement(infoAgree)
					.user_email_agreement(emailAgree)
					.user_sms_agreement(smsAgree)
					.build();
	}
	
}

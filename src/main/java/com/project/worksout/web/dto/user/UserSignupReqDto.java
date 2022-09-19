package com.project.worksout.web.dto.user;

import java.time.LocalDateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.worksout.domain.user.User;

import lombok.Data;

@Data
public class UserSignupReqDto {
	
	@NotBlank
	@Pattern(regexp = "^[가-힇]*$", message = "한글만 입력 가능합니다.")
	private String name;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String birth;
	
	@NotBlank
	@Email
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,16}$", message = "최소 8자, 영어 숫자 특수문자 포함")
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^\\d{2,3}$", message = "핸드폰 번호의 약식과 맞지 않습니다.")
	private String phoneFirst;
	
	@NotBlank
	@Pattern(regexp = "^\\d{3,4}$", message = "핸드폰 번호의 약식과 맞지 않습니다.")
	private String phoneMiddle;
	
	@NotBlank
	@Pattern(regexp = "^\\d{4}$", message = "핸드폰 번호의 약식과 맞지 않습니다.")
	private String phoneLast;
	
	@AssertTrue
	private boolean qualificationAgree;
	
	@AssertTrue
	private boolean infoAgree;
	
	private boolean emailAgree;
	
	private boolean smsAgree;
	
	@AssertTrue
	private boolean checkUsernameFlag;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
	
	public User toEntity() {
		return User.builder()
					.user_name(name)
					.user_gender(gender)
					.user_birth(birth)
					.user_id(username)
					.user_password(new BCryptPasswordEncoder().encode(password))
					.user_phone_first(phoneFirst)
					.user_phone_middle(phoneMiddle)
					.user_phone_last(phoneLast)
					.user_roles("ROLE_ADMIN")
					.user_rank("BASIC")
					.user_qualification_agreement(qualificationAgree)
					.user_info_agreement(infoAgree)
					.user_email_agreement(emailAgree)
					.user_sms_agreement(smsAgree)
					.consignee_phone_first("")
					.consignee_phone_middle("")
					.consignee_phone_last("")
					.build();
	}
	
}

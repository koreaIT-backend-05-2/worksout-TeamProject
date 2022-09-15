package com.project.worksout.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsernameCheckReqDto {

	@NotBlank
	@Email
	private String username;
	
}

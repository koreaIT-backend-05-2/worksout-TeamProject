package com.project.worksout.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String user_name;
	private int user_gender;
	private String user_birth;
	private String user_id;
	private String user_password;
	private String user_phone;
	private String user_roles;
	private String user_level;
	private boolean user_qualification_agreement;
	private boolean user_info_agreement;
	private boolean user_email_agreement;
	private boolean user_sms_agreement;
	private String user_address01;
	private String user_address02;
	private String user_address03;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	public List<String> getUserRoles() {

//		user_roles 가 없으면 새로운ArrayList 를 생성해주고 있으면 공백을 없애고 , 있으면 ,로 구분해줌
		if(user_roles == null || user_roles.isBlank()) {
			return new ArrayList<String>();
		}
		return Arrays.asList(user_roles.replaceAll(" ", "").split(","));
	}
}

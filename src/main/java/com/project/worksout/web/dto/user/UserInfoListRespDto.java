package com.project.worksout.web.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoListRespDto {

	private int userCode;
	private String name;
	private String email;
	private String password;
	private String gender;
	
	private String phoneFirst;
	private String phoneMiddle;
	private String phoneLast;
	
	private String birth;
	private String rank;
	private boolean emailAgree;
	private boolean smsAgree;
	
}

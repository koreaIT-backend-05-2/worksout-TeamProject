package com.project.worksout.service.auth;

import com.project.worksout.web.dto.user.UsernameCheckReqDto;

public interface AuthService {
	public boolean signup() throws Exception;
	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception;
}

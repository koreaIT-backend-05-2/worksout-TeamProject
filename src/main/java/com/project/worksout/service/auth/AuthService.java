package com.project.worksout.service.auth;

import com.project.worksout.web.dto.user.UpdateUserReqDto;
import com.project.worksout.web.dto.user.UsernameCheckReqDto;

public interface AuthService {
	public boolean signup() throws Exception;
	
	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception;
	
	public boolean updateUser(UpdateUserReqDto updateUserReqDto) throws Exception;

	public boolean removeUser(int userCode)throws Exception;
}

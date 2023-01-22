package com.project.worksout.service.userInfo;

import java.util.List;

import com.project.worksout.web.dto.user.UserInfoListRespDto;

public interface UserInfoService {

	public List<UserInfoListRespDto> getUserInfoList(int page, String searchFlag) throws Exception;
	
}

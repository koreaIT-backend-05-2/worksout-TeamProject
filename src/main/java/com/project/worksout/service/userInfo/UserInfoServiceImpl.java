package com.project.worksout.service.userInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.user.UserRepository;
import com.project.worksout.web.dto.user.UserInfoListRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{

	private final UserRepository userRepository;
	
	@Override
	public List<UserInfoListRespDto> getUserInfoList(int page, String searchFlag) throws Exception{
		int index = (page - 1) * 10;
		
		Map<String, Object> map  = new HashMap<String, Object>();
		
		map.put("index", index);
		map.put("search_flag", searchFlag);
		
		List<UserInfoListRespDto> list = new ArrayList<UserInfoListRespDto>();
		
		userRepository.getUserList(map).forEach(user -> {
			list.add(user.toUserListDto());
		});
		
		return list;
	}


}

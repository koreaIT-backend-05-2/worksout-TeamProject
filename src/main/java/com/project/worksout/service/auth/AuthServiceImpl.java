package com.project.worksout.service.auth;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.user.UserRepository;
import com.project.worksout.web.dto.user.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;

	@Override
	public boolean signup() throws Exception {
		return false;
	}

	@Override
	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception {
		
		return userRepository.findUserByUsername(usernameCheckReqDto.getUsername()) == null;
	}
	
}

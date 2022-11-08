package com.project.worksout.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.worksout.domain.user.User;
import com.project.worksout.domain.user.UserRepository;
import com.project.worksout.web.dto.user.UserSignupReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = null;
		try {
			userEntity = userRepository.findUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(username + "사용자 이름은 사용할 수 없습니다.");
		}
		
		return new PrincipalDetails(userEntity);
	}
	

	public boolean addUser(UserSignupReqDto userSignupReqDto) throws Exception{
		return userRepository.save(userSignupReqDto.toEntity()) > 0;
		
	}
	

}

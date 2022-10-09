package com.project.worksout.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int save(User user) throws Exception;
	
	public User findUserByUsername(String username) throws Exception;
	
	public int updateUserByUsercode(User user) throws Exception; 

	public  int removeUserByUsercode(int userCode) throws Exception;
	

}

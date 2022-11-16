package com.project.worksout.domain.interest;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestRepository {

	public int saveInterest(Interest interest) throws Exception;
}

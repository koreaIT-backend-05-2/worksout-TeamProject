package com.project.worksout.domain.interest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestRepository {

	public int saveInterest(Interest interest) throws Exception;
	
	public List<Interest> getCheckInterestProduct(int productGroup) throws Exception;
}

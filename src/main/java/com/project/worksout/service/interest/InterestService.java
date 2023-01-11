package com.project.worksout.service.interest;

import java.util.List;

import com.project.worksout.web.dto.interest.GetinterestRespDto;
import com.project.worksout.web.dto.interest.InterestRespdto;

public interface InterestService {

	public boolean addInterestProduct(InterestRespdto interestRespdto) throws Exception;

	//flag 가 1일 때 상품에 하트가 들어와있게 하는 로직
	public List<GetinterestRespDto> checkInterestProduct(int productGroup)throws Exception;

}

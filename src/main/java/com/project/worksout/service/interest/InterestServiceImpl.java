package com.project.worksout.service.interest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.interest.InterestRepository;
import com.project.worksout.web.dto.interest.GetinterestRespDto;
import com.project.worksout.web.dto.interest.InterestRespdto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService{

	private final InterestRepository interestRepository;
	
	@Override
	public boolean addInterestProduct(InterestRespdto interestRespdto) throws Exception {
		
		return interestRepository.saveInterest(interestRespdto.toInterestEntity()) > 0;
	}

	//flag 가 1일 때 상품에 하트가 들어와있게 하는 로직
	@Override
	public List<GetinterestRespDto> checkInterestProduct(int productGroup) throws Exception {
		
		List<GetinterestRespDto> interestList = new ArrayList<GetinterestRespDto>();
		
		interestRepository.getCheckInterestProduct(productGroup).forEach(interest -> {
			interestList.add(interest.tocheckFlagEntity());
		});
		
		return interestList;
	}
	
}

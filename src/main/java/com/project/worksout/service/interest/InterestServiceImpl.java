package com.project.worksout.service.interest;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.interest.InterestRepository;
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

	
}

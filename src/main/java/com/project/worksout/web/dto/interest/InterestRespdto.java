package com.project.worksout.web.dto.interest;

import com.project.worksout.domain.interest.Interest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterestRespdto {

	private int userCode;
	private int productGroup;
	private boolean interestFlag;
	
	public Interest toInterestEntity() {
		return Interest.builder()
				.user_code(userCode)
				.product_group(productGroup)
				.interest_flag(interestFlag)
				.build();
	}
}

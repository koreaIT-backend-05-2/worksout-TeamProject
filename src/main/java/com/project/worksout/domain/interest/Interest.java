package com.project.worksout.domain.interest;

import com.project.worksout.web.dto.interest.GetinterestRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interest {

	private int interest_code;
	private int user_code;
	private int product_group;
	private boolean interest_flag;
	
	public GetinterestRespDto tocheckFlagEntity() {
		return GetinterestRespDto.builder()
					.userCode(user_code)
					.productGroup(product_group)
					.interestFlag(interest_flag)
					.build();
	}
}

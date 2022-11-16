package com.project.worksout.web.dto.interest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetinterestRespDto {
	private int userCode;
	private int productGroup;
	private boolean interestFlag;
}

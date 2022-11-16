package com.project.worksout.domain.interest;

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
	private int interest_flag;
}

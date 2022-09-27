package com.project.worksout.domain.inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryFile {

	private int file_code;
	private int inquiry_code;
	private String file_name;
	
}

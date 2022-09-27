package com.project.worksout.domain.inquiry;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {

	private int inquiry_code;
	private String inquiry_kind;
	private String inquiry_title;
	private String inquiry_name;
	private String inquiry_email;
	private String inquiry_content;
	
	private int file_code;
	private String file_name;
	private LocalDateTime create_date;
	
}

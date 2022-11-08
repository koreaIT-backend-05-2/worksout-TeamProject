package com.project.worksout.domain.inquiry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.worksout.web.dto.inquiry.GetinquiryListRespDto;

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
	private String inquiry_name;
	private String inquiry_email;
	private String inquiry_title;
	private String inquiry_content;
	
	private int file_code;
	private String file_name;
	private LocalDateTime create_date;
	
	private int total_inquiry_count;
	
	public GetinquiryListRespDto toListDto() {
		return GetinquiryListRespDto.builder()
																.inquiryCode(inquiry_code)
																.inquiryKind(inquiry_kind)
																.inquiryName(inquiry_name)
																.inquiryEmail(inquiry_email)
																.inquiryTitle(inquiry_title)
																.createDate(create_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
																.totalInquiryCount(total_inquiry_count)
																.build();
	}
	
}

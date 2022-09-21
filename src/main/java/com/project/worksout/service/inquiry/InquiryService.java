package com.project.worksout.service.inquiry;

import com.project.worksout.web.dto.inquiry.AddInquiryReqDto;

public interface InquiryService {

	public int addInquiry(AddInquiryReqDto addInquiryReqDto) throws Exception;
}

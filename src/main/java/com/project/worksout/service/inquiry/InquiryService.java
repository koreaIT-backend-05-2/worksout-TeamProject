package com.project.worksout.service.inquiry;

import java.util.List;

import com.project.worksout.web.dto.inquiry.AddInquiryReqDto;
import com.project.worksout.web.dto.inquiry.GetInquiryRespDto;

public interface InquiryService {

	public int addInquiry(AddInquiryReqDto addInquiryReqDto) throws Exception;
	public GetInquiryRespDto getInquiry(String flag, int inquiryCode) throws Exception;

}

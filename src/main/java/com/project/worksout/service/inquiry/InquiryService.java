package com.project.worksout.service.inquiry;

import java.util.List;

import com.project.worksout.web.dto.inquiry.AddInquiryReqDto;
import com.project.worksout.web.dto.inquiry.GetInquiryRespDto;
import com.project.worksout.web.dto.inquiry.GetinquiryListRespDto;

public interface InquiryService {

	public List<GetinquiryListRespDto> getinquiryList(int page, String searchFlag) throws Exception;
	public int addInquiry(AddInquiryReqDto addInquiryReqDto) throws Exception;
	public GetInquiryRespDto getInquiry(String flag, int inquiryCode) throws Exception;

}

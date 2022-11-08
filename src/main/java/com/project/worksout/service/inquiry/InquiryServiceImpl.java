package com.project.worksout.service.inquiry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.worksout.domain.inquiry.Inquiry;
import com.project.worksout.domain.inquiry.InquiryFile;
import com.project.worksout.domain.inquiry.InquiryRepository;
import com.project.worksout.web.dto.inquiry.AddInquiryReqDto;
import com.project.worksout.web.dto.inquiry.GetInquiryRespDto;
import com.project.worksout.web.dto.inquiry.GetinquiryListRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor	
public class InquiryServiceImpl implements InquiryService{
	
	@Value("${file.downloadPath}")
	private String downloadFilepath;
	
	private final InquiryRepository inquiryRepository;

	@Override
	public int addInquiry(AddInquiryReqDto addInquiryReqDto) throws Exception {
		Predicate<String> predicate = (fileName)  -> !fileName.isBlank();
		
		Inquiry inquiry = null;
				
		inquiry = Inquiry.builder()
										.inquiry_kind(addInquiryReqDto.getInquiryKind())
										.inquiry_title(addInquiryReqDto.getInquiryTitle())
										.inquiry_name(addInquiryReqDto.getInquiryName())
										.inquiry_email(addInquiryReqDto.getInquiryEmail())
										.inquiry_content(addInquiryReqDto.getInquiryContent())
										.build();
		
		inquiryRepository.saveInquiry(inquiry);
		
		if(predicate.test(addInquiryReqDto.getInquiryFile().get(0).getOriginalFilename())) {
			
			List<InquiryFile> inquiryFiles = new ArrayList<InquiryFile>();
			
			
			
//			List<String> cancleFile = new ArrayList<String>();
//			cancleFile.addAll(addInquiryReqDto.getCancleFile());
//			
//			
//			testList.add("화면 캡처 2022-03-17 121509.png");
//			
//			for(String fileStr : cancleFile) {
//				addInquiryReqDto.getInquiryFile().removeIf(file -> file.getOriginalFilename().equals(fileStr));
//				
//			}
			
			
			for(MultipartFile file : addInquiryReqDto.getInquiryFile()) {
				String originalFilename = file.getOriginalFilename();
				String tempFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFilename;
				log.info(tempFilename);
				Path uploadPath = Paths.get(downloadFilepath, "inquiry/" + tempFilename);
				
				File f = new File(downloadFilepath + "inquiry");
				if(!f.exists()) {
					f.mkdirs();	
				}
				
				try {
					Files.write(uploadPath, file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
					inquiryFiles.add(InquiryFile.builder()
																		.inquiry_code(inquiry.getInquiry_code())
																		.file_name(tempFilename)
																		.build());	
			}
			inquiryRepository.saveInquiryFiles(inquiryFiles);
		}
		return inquiry.getInquiry_code();
	}
	
	
	
	@Override
	public GetInquiryRespDto getInquiry(String flag, int inquiryCode) throws Exception {
		GetInquiryRespDto getInquiryRespDto = null;
		
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("flag", flag);
		reqMap.put("inquiry_code", inquiryCode);
		
		List<Inquiry> inquiries = inquiryRepository.getInquiry(reqMap);
		
		if(!inquiries.isEmpty()) {
			List<Map<String, Object>> downloadFiles = new ArrayList<Map<String,Object>>();
			
			inquiries.forEach(inquiry -> {
				Map<String, Object> fileMap = new HashMap<String, Object>();
				String fileName = inquiry.getFile_name();
				
				if(fileName != null) {
					fileMap.put("fileCode", inquiry.getFile_code());
					fileMap.put("fileName", fileName.substring(fileName.indexOf("_") + 1));
				}
				
				downloadFiles.add(fileMap);
			});
			
			
			Inquiry firstInquiry = inquiries.get(0);
			
			
			getInquiryRespDto = GetInquiryRespDto.builder()
					.inquiryCode(firstInquiry.getInquiry_code())
					.inquiryKind(firstInquiry.getInquiry_kind())
					.inquiryTitle(firstInquiry.getInquiry_title())
					.inquiryName(firstInquiry.getInquiry_name())
					.inquiryEmail(firstInquiry.getInquiry_email())
					.inquiryContent(firstInquiry.getInquiry_content())
					.createDate(firstInquiry.getCreate_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
					.downloadFiles(downloadFiles)
					.build();
		}
		
		return getInquiryRespDto;
	}
	
	@Override
	public List<GetinquiryListRespDto> getinquiryList(int page, String searchFlag) throws Exception {
		int index = (page - 1) * 10;
		
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("index", index);
		map.put("search_flag", searchFlag);
		
		log.info("test {}: ", searchFlag);
		
		List<GetinquiryListRespDto> list = new ArrayList<GetinquiryListRespDto>();
		
		inquiryRepository.getInquiryList(map).forEach(inquiry -> {
			list.add(inquiry.toListDto());
		});
		
		return list;
	}
}

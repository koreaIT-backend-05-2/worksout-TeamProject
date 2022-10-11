package com.project.worksout.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.project.worksout.service.auth.PrincipalDetails;
import com.project.worksout.web.dto.user.UpdateUserReqDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int user_code;
	private String user_name;
	private String user_gender;
	private String user_birth;
	private String user_id;
	private String user_password;
	private String user_phone_first;
	private String user_phone_middle;
	private String user_phone_last;
	private String user_roles;
	private String user_rank;
	private boolean user_qualification_agreement;
	private boolean user_info_agreement;
	private boolean user_email_agreement;
	private boolean user_sms_agreement;
	
	private String delivery_name;
	private String consignee_name;
	private String consignee_phone_first;
	private String consignee_phone_middle;
	private String consignee_phone_last;
	private String delivery_address;
	private String delivery_detail_address;
	private String delivery_requestment;
	
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	public List<String> getUserRoles() {

//		user_roles 가 없으면 새로운ArrayList 를 생성해주고 있으면 공백을 없애고 , 있으면 ,로 구분해줌
		if(user_roles == null || user_roles.isBlank()) {
			return new ArrayList<String>();
		}
		return Arrays.asList(user_roles.replaceAll(" ", "").split(","));
	}
	
	//update된 principal을 다시 불러주는 메소드
	public void modifyUserData(UpdateUserReqDto updateUserReqDto) {
		
		BiConsumer<Object, Object> convert = (filed, dto) -> {
			filed = dto != null ? dto : filed;
			
		};
		
		System.out.println(user_name);
		
		convert.accept(user_name, updateUserReqDto.getName());
		convert.accept(user_gender, updateUserReqDto.getGender());
		convert.accept(user_phone_first, updateUserReqDto.getPhoneFirst());
		convert.accept(user_phone_middle, updateUserReqDto.getPhoneMiddle());
		convert.accept(user_phone_last, updateUserReqDto.getPhoneLast());
		convert.accept(user_email_agreement, updateUserReqDto.isEmailAgree());
		convert.accept(user_sms_agreement, updateUserReqDto.isSmsAgree());
		convert.accept(delivery_name, updateUserReqDto.getDeliveryName());
		convert.accept(consignee_name, updateUserReqDto.getConsigneeName());
		convert.accept(consignee_phone_first, updateUserReqDto.getConsigneePhoneFirst());
		convert.accept(consignee_phone_middle, updateUserReqDto.getConsigneePhoneMiddle());
		convert.accept(consignee_phone_last, updateUserReqDto.getConsigneePhoneLast());
		convert.accept(delivery_address, updateUserReqDto.getDeliveryAddress());
		convert.accept(delivery_detail_address, updateUserReqDto.getDeliveryDetailAddress());
		convert.accept(delivery_requestment, updateUserReqDto.getDeliveryRequestment());

		System.out.println(user_name);
	}

}

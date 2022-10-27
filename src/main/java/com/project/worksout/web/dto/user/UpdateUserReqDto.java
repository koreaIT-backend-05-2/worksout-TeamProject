package com.project.worksout.web.dto.user;

import java.time.LocalDateTime;

import com.project.worksout.domain.user.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserReqDto {
	
	private int userCode;
	private String name;
	private String gender;
	private String phoneFirst;
	private String phoneMiddle;
	private String phoneLast;
	private String rank;
	private boolean emailAgree;
	private boolean smsAgree;

	private String deliveryName;
	private String consigneeName;
	private String consigneePhoneFirst;
	private String consigneePhoneMiddle;
	private String consigneePhoneLast;
	private String deliveryAddress;
	private String deliveryDetailAddress;
	private String deliveryRequestment;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	public User toEntity() {
		return User.builder()
				.user_code(userCode)
				.user_name(name)
				.user_gender(gender)
				.user_phone_first(phoneFirst)
				.user_phone_middle(phoneMiddle)
				.user_phone_last(phoneLast)				
				.user_rank(rank)
				.user_email_agreement(emailAgree)
				.user_sms_agreement(smsAgree)
				.delivery_name(deliveryName)
				.consignee_name(consigneeName)
				.consignee_phone_first(consigneePhoneFirst)
				.consignee_phone_middle(consigneePhoneMiddle)
				.consignee_phone_last(consigneePhoneLast)
				.delivery_address(deliveryAddress)
				.delivery_detail_address(deliveryDetailAddress)
				.delivery_requestment(deliveryRequestment)
				.build();
	}
	
}

const addressInput = document.querySelector(".address-input");
const addressSearchButton = document.querySelector(".address-search-button");
const detailAddressInput = document.querySelector(".detail-address-input");



// 다음 주소 api
function searchAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    
                } 

                addressInput.value = addr;
                // 커서를 상세주소 필드로 이동한다.
                detailAddressInput.focus();
            }
        }).open();
    }
    
    
//신규 입력

const newInputBtn = document.querySelector(".new-input-button");
    
 //input 비우기
newInputBtn.onclick = () => {
	
}
    
//검색창 클릭시 주소검색
addressSearchButton.onclick = () => {
	
	searchAddress();
	
}

//user 정보 담기
const username = document.querySelector("#username");
const gender = document.querySelector("#gender");
const userCode = document.querySelector("#usercode");
const userRank = document.querySelector("#userrank");
const usercode = document.querySelector("#usercode");
const editPhoneInputs = document.querySelectorAll(".edit-phone input");

// user 배송정보 담기
const locationName = document.querySelector(".location-name");
const consignee = document.querySelector("#consignee");
const addressPhoneInputs = document.querySelectorAll(".address-phone input");
const requestTerm = document.querySelector("#request-term");

console.log(requestTerm);

//유저 휴대폰
username.value = `${user.user_name}`;
gender.value = `${user.user_gender}`;
userCode.value = `${user.user_code}`;
userRank.value = `${user.user_rank}`;
editPhoneInputs[0].value = `${user.user_phone_first} `;
editPhoneInputs[1].value = `${user.user_phone_middle} `;
editPhoneInputs[2].value = `${user.user_phone_last} `;
emailAgree = `${user.user_email_agreement}`;
smsAgree = `${user.user_sms_agreement} `;


addressPhoneInputs[0].value = `${user.consignee_phone_first}`
addressPhoneInputs[1].value = `${user.consignee_phone_middle}`
addressPhoneInputs[2].value = `${user.consignee_phone_last}`
locationName.value = `${user.delivery_name}`;
consignee.value = `${user.consignee_name}`;
addressInput.value = `${user.delivery_address}`;
detailAddressInput.value = `${user.delivery_detail_address}`;
requestTerm.vlaue = `${user.delivery_requestment}`;

//회원정보 수정 버튼
const modifyBtn = document.querySelector(".modify-button");


//수정하기 버튼 클릭시 회원정보 수정
modifyBtn.onclick = () => {
	load();
}

//마케팅 정보 수신동의 flag

const checkboxs = document.querySelectorAll(".checkbox-agree");
/**

let agreeList = [false, false];

for(let i = 0; i < agreeList.length; i++) {
	checkboxs[i].onchange = () => {
		agreeList[i] = true;
		checkboxs[i].checked = true;
	}
}

 */
 
 //회원 탈퇴 버튼 (모달 컨테이너로 유도 후 탈퇴)
const modalContainer = document.querySelector(".modal-container")
const withdrawalBtn = document.querySelector(".withdrawal-button");
const modalWithdrawalBtn = document.querySelector(".modal-withdrawal-button");
const coloseModal = document.querySelector(".modal-close-group i");

withdrawalBtn.onclick = () => {
		modalContainer.classList.remove("modal-visible");
	
		modalDeleteUser();
		
}

coloseModal.onclick = () => {
	modalContainer.classList.add("modal-visible");
}

function modalDeleteUser() {
	
	modalWithdrawalBtn.onclick = () => {
		deleteUser();
	}
}

  
function load() {
	
	const shippingInfoInputs = document.querySelectorAll(".shipping-info input");
	
	let updateData = {
		name : username.value,
		gender: gender.value,
		userCode: user.user_code,
		rank: user.user_rank,
		phoneFirst: editPhoneInputs[0].value,
		phoneMiddle: editPhoneInputs[1].value,
		phoneLast: editPhoneInputs[2].value,
		emailAgree: checkboxs[0].checked,
		smsAgree: checkboxs[1].checked,
		deliveryName: shippingInfoInputs[0].value,
		consigneeName: shippingInfoInputs[1].value,
		consigneePhoneFirst:  addressPhoneInputs[0].value,
		consigneePhoneMiddle:  addressPhoneInputs[1].value,
		consigneePhoneLast:  addressPhoneInputs[2].value,
		deliveryAddress: shippingInfoInputs[5].value,
		deliveryDetailAddress: shippingInfoInputs[6].value,
		deliveryRequestment: shippingInfoInputs[7].value,
	}
	
	$.ajax({
		async: false,
		type: "put",
		url: `/api/v1/auth/modify/${user.user_code}`,
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(updateData),
		success: (response) => {
			if(response.data){
				alert("회원정보 수정이 완료되었습니다.");
				location.replace("/mypage/modify");			
			}
		},		
		error: errorMessage
		
	});
}

 function deleteUser() {
	
	$.ajax({
		async: false,
		type: "delete",
		url: `/api/v1/auth/remove/${user.user_code}`,
		dataType: "json",
		success: (response) => {
			if(response.data) {
				alert("회원 탈퇴가 완료되었습니다.");
				location.replace("/signin");
			}
		},
		error: errorMessage
	});
	
}

//에러메세지
function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}

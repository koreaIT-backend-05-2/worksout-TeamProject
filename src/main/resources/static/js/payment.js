const ordererNameInput = document.querySelector(".orderer-name-input");
const userPhoneNumInputs = document.querySelectorAll(".phone-number-container input");
const shippingLocationInput = document.querySelector(".shipping-location-input");
const consigneeNameInput = document.querySelector(".consignee-name-input");
const consigneePhoneNumInputs = document.querySelectorAll(".consignee-phone-group input");
const addressInput = document.querySelector(".address-input");
const detailAddressInput = document.querySelector(".detail-address-input");

const shippingRequest = document.querySelector(".shipping-request");

console.log(ordererNameInput)
console.log(userPhoneNumInputs)
console.log(user.user_name)
console.log(shippingLocationInput)
console.log(addressInput)

loadInfo();

function loadInfo() {
	
	ordererNameInput.value = user.user_name;
	userPhoneNumInputs[0].value = user.user_phone_first;
	userPhoneNumInputs[1].value = user.user_phone_middle;
	userPhoneNumInputs[2].value = user.user_phone_last;
	shippingLocationInput.value = user.delivery_name;
	consigneeNameInput.value = user.consignee_name;
	consigneePhoneNumInputs[0].value = user.consignee_phone_first;
	consigneePhoneNumInputs[1].value = user.consignee_phone_middle;
	consigneePhoneNumInputs[2].value = user.consignee_phone_last;
	addressInput.value = user.delivery_address;
	detailAddressInput.value = user.delivery_detail_address;
	
}

const addressSearchBtn = document.querySelector(".address-search-button");

addressSearchBtn.onclick = () => {
	searchAddress();
}


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
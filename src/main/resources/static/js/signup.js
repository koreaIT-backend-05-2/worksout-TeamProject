// 생년월일 로직
const year = document.querySelector('#year');
const month = document.querySelector('#month');
const date = document.querySelector('#date');


// 연도 선택시 월 활성화

year.onchange = () => {

  if(year.value == "year") {
      month.disabled = true;
      date.disabled = true;
  }else if(month.value == "month") {
      month.disabled = false;
      activeMonthSelect();
      date.disabled = true;
  }
}

// 월 선택시 일 활성화

month.onchange = () => {

if(date.value == "date") {
	activeDateSelect();
	date.disabled = false;
	}
}

// "월"의 스타일
function activeMonthSelect() {
	month.style.backgroundColor = "white"
	month.style.border = "1px solid #9ca3af"
	month.style.color = "#1F2937"
}

//"일"의 스타일
function activeDateSelect() {
	date.style.backgroundColor = "white"
	date.style.border = "1px solid #9ca3af"
	date.style.color = "#1F2937"
}

/////////////////////////////////////////////////////////////

// option 목록 생성 여부 확인
isYearOptionExisted = false;

// '출생 연도' 셀렉트 박스 option 목록 동적 생성
year.onfocus = () => {
  // year 목록 생성되지 않았을 때 (최초 클릭 시)
  if(!isYearOptionExisted) {
    isYearOptionExisted = true
    for(var i = 2022; i >= 1900; i--) {
      // option element 생성
      const YearOption = document.createElement('option')
      YearOption.setAttribute('value', i)
      YearOption.innerText = i
      // birthYearEl의 자식 요소로 추가
      year.appendChild(YearOption);
    }
  }
}

isMonthOptionExisted = false;

// '출생 달' 셀렉트 박스 option 목록 동적 생성
month.onfocus = () => {
  // month 목록 생성되지 않았을 때 (최초 클릭 시)
  if(!isMonthOptionExisted) {
    isMonthOptionExisted = true
    for(var i = 1; i <= 12; i++) {
      // option element 생성
      const monthOption = document.createElement('option')
      monthOption.setAttribute('value', i)
      monthOption.innerText = i
      // birthYearEl의 자식 요소로 추가
      month.appendChild(monthOption);
    }
  }
}
  
isDateOptionExisted = false;

  // '출생 일' 셀렉트 박스 option 목록 동적 생성
  date.onfocus = () => {

    // date 목록 생성되지 않았을 때 (최초 클릭 시)
    if(!isDateOptionExisted) {
      isDateOptionExisted = true
  
      if(month.value == "1" || month.value == "3" || month.value == "5" 
          || month.value == "7" || month.value == "8" || month.value == "10" 
          || month.value == "12") {
  
            for(var i = 1; i <= 31; i++) {
  
              // option element 생성
              const dateOption = document.createElement('option')
              dateOption.setAttribute('value', i)
              dateOption.innerText = i
              // birthYearEl의 자식 요소로 추가
              date.appendChild(dateOption);
            }
  
      }else {
            for(var i = 1; i <= 30; i++) {
  
              // option element 생성
              const dateOption = document.createElement('option')
              dateOption.setAttribute('value', i)
              dateOption.innerText = i
              // birthYearEl의 자식 요소로 추가
              date.appendChild(dateOption);
            }
      }
    }
  }

console.log(year.value);
console.log(month.value);
console.log(date.value);

/**

	아이디 중복 확인

 */

const inputs = document.querySelectorAll("input");

console.log(inputs);
console.log(inputs.value)

let checkUsernameFlag = false;

inputs[1].onblur = () => {
	
	$.ajax({
		async : false,
		type: "get",
		url: "/api/v1/auth/signup/validation/username",
		data: {username: inputs[1].value},
		datatype: "json",
		success: (response) => {
			checkUsernameFlag = response.data;

			if(response.data) {
				alert("사용 가능한 아이디 입니다.")
			}else {
				alert("이미 가입된 아이디 입니다.")
			}
		},
		error: (error) => {
	      if(error.status == 400) {
	        alert("잘못된 형식의 아이디입니다.");
	      }else {
	        errorMessage
	      }
	    }
	});
}

/**

	비밀번호 확인

 */

const password = document.querySelector("#password");
const passwordCheck = document.querySelector("#password-check");
let passwordCheckError = document.querySelector(".password-check-error");

password.onblur = () => {
	
	if(password.value != passwordCheck.value){
		passwordCheck.style.border = "1px solid red";
		passwordCheckError.innerHTML = "비밀번호를 확인해주세요."
	}
}

passwordCheck.onkeyup = () => {
	
	if(password.value == passwordCheck.value) {
		passwordCheck.style.border = "";
		passwordCheckError.innerHTML = ""
	}
}


/**

	약관동의 체크박스 체크

 */
 
const checkboxs = document.querySelectorAll(".checkbox-agree");
const allAgree = document.querySelector("#all-agree")
let agreeList = [false, false, false, false, false];
let agreeFlag = false;

console.log("전체동의: " + allAgree)

agreeCheck();

function agreeCheck() {
	
	allAgree.onclick = () => {
		
		if(allAgree.checked){
			for(let i = 0; i < agreeList.length; i++) {
				agreeList[i] = true;
				checkboxs[i].checked = true;
			}
		}else {
			for(let i = 0; i < agreeList.length; i++) {
				agreeList[i] = false;
				checkboxs[i].checked = false;
			}
		}
	}
}

for(let i = 0; i < agreeList.length; i++) {
	checkboxs[i].onchange = () => {
		agreeList[i] = checkboxs[i].checked;
	}
}

// 회원가입

const signupBtn = document.querySelector(".signup-button");
const selects = document.querySelectorAll("select");
const authNumber = document.querySelector(".auth-number");


authNumber.onclick = () => {
	
}


signupBtn.onclick = () => {
	
	let birthData = selects[1].value + "-" + selects[2].value + "-" + selects[3].value
	let usernameError = document.querySelector(".username-error");
	let genderError = document.querySelector(".gender-error");
	let userIdError = document.querySelector(".userId-error")
	let passwordError = document.querySelector(".password-error");
	let phoneError = document.querySelector(".phone-error");
	let qualificationAgreeError = document.querySelector(".qualification-agree-error");
	let infoAgreeError = document.querySelector(".info-agree-error");
	
	
	let signupData = {
		name: inputs[0].value,
		gender: selects[0].value,
		birth: birthData,
		username: inputs[1].value,
		password: inputs[2].value,
		phoneFirst: inputs[4].value,
		phoneMiddle: inputs[5].value,
		phoneLast: inputs[6].value,
		"qualificationAgree": checkboxs[1].checked,
		"infoAgree": checkboxs[2].checked,
		"emailAgree": checkboxs[3].checked,
		"smsAgree": checkboxs[4].checked,
		"checkUsernameFlag": checkUsernameFlag
	}
	
	//이름 공백시 오류
	function nameErrorMsg() {
		if(signupData.name == ""){
			inputs[0].style.border = "1px solid red";
			usernameError.innerHTML = "이름을 입력해주세요."
		} else {
			inputs[0].style.border = "";
			usernameError.innerHTML = ""
		}
	}
	
	
	
	//성별 공백시 오류
	function genderErrorMsg() {
		if(signupData.gender == ""){
			selects[0].style.border = "1px solid red";
			genderError.innerHTML = "성별을 선택해주세요."
		}else {
			selects[0].style.border = "";
			genderError.innerHTML = ""
		}
	}
	
	//아이디 공백시 오류
	function userIdErrorMsg() {
		if(signupData.username == ""){
			inputs[1].style.border = "1px solid red";
			userIdError.innerHTML = "아이디를 입력해주세요."
		}else {
			inputs[1].style.border = "";
			userIdError.innerHTML = ""
		}
	}
	
	//비밀번호 공백시 오류
	function passwordErrorMsg() {
		if(signupData.password == ""){
				inputs[2].style.border = "1px solid red";
				passwordError.innerHTML = "비밀번호를 입력해주세요."
			}else {
				inputs[2].style.border = "";
				passwordError.innerHTML = ""
			}
	}
	
	//비밀번호 확인 불일치 및 공백시 오류
	function passwordCheckErrorMsg() {
		if(signupData.password != inputs[3].value || signupData.password == ""){
				inputs[3].style.border = "1px solid red";
				passwordCheckError.innerHTML = "비밀번호를 확인해주세요."
		}else {
			inputs[3].style.border = "";
			passwordCheckError.innerHTML = ""
		}
	}
	
	//휴대폰 번호 공백시 오류
	function phoneErrorMsg() {
		if(signupData.phone == "--"){
				inputs[4].style.border = "1px solid red";
				inputs[5].style.border = "1px solid red";
				inputs[6].style.border = "1px solid red";
				phoneError.innerHTML = "휴대폰번호를 인증해주세요."
		}else {
			inputs[4].style.border = "";
			inputs[5].style.border = "";
			inputs[6].style.border = "";
			phoneError.innerHTML = ""
		}
	}
	
	//이용약관 체크 안할시 오류
	function qualificationAgreeErrorMsg() {
		if(checkboxs[1].checked == false){
				qualificationAgreeError.innerHTML = "이용약관을 체크해주세요."
			}else {
				qualificationAgreeError.innerHTML = ""
			}
	}
	
	//개인정보 약관 체크 안할시 오류
	function infoAgreeErrorMsg() {
		if(checkboxs[2].checked == false){
				infoAgreeError.innerHTML = "이용약관을 체크해주세요."
			}else {
				infoAgreeError.innerHTML = ""
			}
	}
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/auth/signup",
		contentType: "application/json",
		data: JSON.stringify(signupData),
		datatype: "json",
		success: (response) => {
			if(response.data) {
				alert("회원가입 완료")
				location.replace("/signup/success");
			}
		},
		error: (error) => {
	      if(error.status == 400) {
			
				nameErrorMsg();
				
				genderErrorMsg();
			
				userIdErrorMsg();
				
				passwordErrorMsg();
				
				passwordCheckErrorMsg();
				
				phoneErrorMsg();
				
				qualificationAgreeErrorMsg();
				
				infoAgreeErrorMsg();
				
				alert(JSON.stringify(error.responseJSON.data));
				
	      }else {
	        errorMessage
	      }
	    }
	});
}

/**

	인증번호 발송

 */
const sendAuthNum = document.querySelector(".auth-number");
const authNumGroup = document.querySelector(".auth-number-group");
const authNumCheckBtn = document.querySelector(".auth-number-button");
const authNumInput = document.querySelector(".auth-number-group-input");


console.log("1234: " + authNumInput);
console.log("test: " + authNumCheckBtn);
console.log("인증번호 버튼: " + sendAuthNum);

sendAuthNum.onclick = () => {
	authNumGroup.classList.add("visible")
	sendAuthNum.innerHTML = "인증번호 재발송"
	
	const to = inputs[4].value + inputs[5].value + inputs[6].value;
	
	$.ajax({
		async: false,
		url: "/check/sendSMS",
		type: "get",
		data: {
			"to": to
		},
		success: function(data) {
			const checkNum = data;
			
			authNumCheckBtn.onclick = () => {
				let userNum = document.querySelector(".auth-number-group-input").value;
				console.log(typeof checkNum);
				console.log(typeof userNum);
				console.log(checkNum)
				console.log(userNum)
				if(checkNum === userNum) {
					alert("인증에 성공하셨습니다.")
					authNumGroup.classList.remove("visible")
					inputs[4].disabled = "true";
					inputs[5].disabled = "true";
					inputs[6].disabled = "true";
					sendAuthNum.innerHTML = "인증확인 완료";
					sendAuthNum.disabled = "false";
				}else {
					alert("인증에 실패하셨습니다. 다시 입력해주세요")
				}
			}
		},
		error: errorMessage
			
	});
	
}




//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}
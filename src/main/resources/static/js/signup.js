// 생년월일 로직
const year = document.querySelector('#year');
const month = document.querySelector('#month');
const date = document.querySelector('#date');



// option 목록 생성 여부 확인
isYearOptionExisted = false;

// '출생 연도' 셀렉트 박스 option 목록 동적 생성
year.onfocus = () => {
  // year 목록 생성되지 않았을 때 (최초 클릭 시)
  if(!isYearOptionExisted) {
    isYearOptionExisted = true
    for(var i = 1900; i <= 2022; i++) {
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
  // year 목록 생성되지 않았을 때 (최초 클릭 시)
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
  // year 목록 생성되지 않았을 때 (최초 클릭 시)
  if(!isDateOptionExisted) {
    isDateOptionExisted = true
    for(var i = 1; i <= 31; i++) {
      // option element 생성
      const dateOption = document.createElement('option')
      dateOption.setAttribute('value', i)
      dateOption.innerText = i
      // birthYearEl의 자식 요소로 추가
      date.appendChild(dateOption);
    }
  }
}

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






console.log(year.value);
console.log(month.value);
console.log(date.value);
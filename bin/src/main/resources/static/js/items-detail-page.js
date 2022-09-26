const sizeBtns = document.querySelectorAll(".size-button");
const selectBtn = document.querySelector(".select-button");

// 사이즈 클릭시만 구매 또는 장바구니 버튼 생성

selectBtn.onclick = () => {
    alert("사이즈를 선택해주세요");
}

for(let i = 0; i < sizeBtns.length; i++) {
    sizeBtns[i].onclick = () => {
	// 클릭 이전에 한번 초기화
		sizeBtns.forEach(sizeBtn => {
			sizeBtn.classList.remove("act-size")
		});
		
	// 이벤트 추가
        sizeBtns[i].classList.add("act-size");
      
   	// 사이즈 선택시 구매하기, 장바구니 버튼 나오는 이벤트
        selectBtn.onclick = () =>{
            if(sizeBtns[i].classList.add){
                change();
            }
        }
    }
}


// 사이즈 선택시 구매하기, 장바구니 버튼 나오는 이벤트

function change() {
    const purchaseGroup = document.querySelector(".purchase-group");

        purchaseGroup.innerHTML= "";
        purchaseGroup.innerHTML= `
            <button type="button" class="purchase-button">구매하기</button>
            <button type="button" class="cart-button">장바구니</button>
        `;
}

// 상세정보

const detailInfoBtn = document.querySelector(".detail-info-button");
const detailInfo = document.querySelector(".detail-info");

detailInfoBtn.onclick = () => {
	detailInfo.classList.toggle("act");
}

// 배송안내
const shippingInfoBtn = document.querySelector(".shipping-info-button");
const shippingInfo = document.querySelector(".shipping-info");

shippingInfoBtn.onclick = () => {
	shippingInfo.classList.toggle("act");
}

// 반품안내
const returnInfoBtn = document.querySelector(".return-info-button");
const returnInfo = document.querySelector(".return-info");

returnInfoBtn.onclick = () => {
	returnInfo.classList.toggle("act");
}


// A/S 안내
const asInfoBtn = document.querySelector(".as-info-button");
const asInfo = document.querySelector(".as-info");

asInfoBtn.onclick = () => {
	asInfo.classList.toggle("act");
}

// 주의사항
const precautionsInfoBtn = document.querySelector(".precautions-info-button");
const precautionsInfo = document.querySelector(".precautions-info");

precautionsInfoBtn.onclick = () => {
	precautionsInfo.classList.toggle("act");
}


//관심버튼 눌렀을 때 바뀜

const interestBtn = document.querySelector(".interest-button");

console.log("관심버튼:" + interestBtn);

let flag = false;

interestBtn.onclick = () => {
	if(flag) {
		flag = false;
		interestBtn.innerHTML = `<i class="fa-regular fa-heart" ></i>`;
	}else{
		flag = true;
		interestBtn.innerHTML = `<i class="fa-solid fa-heart" style="color:red;"></i>`;
	}
}

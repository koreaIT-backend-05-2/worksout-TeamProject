const interestBtn = document.querySelector(".interest-button");
const sizeBtnsGroup = document.querySelector(".size-buttons-group");
const sizeBtns = document.querySelectorAll(".size-button");
const selectBtn = document.querySelector(".select-button");

console.log(sizeBtnsGroup);

interestBtn.onclick = () => {
    interestBtn.classList.toggle("interest-toggle");
}

// 사이즈 클릭시만 구매 또는 장바구니 버튼 생성

selectBtn.onclick = () => {
    alert("사이즈를 선택해주세요");
}

for(let i = 0; i < sizeBtns.length; i++) {
    sizeBtns[i].onclick = () => {
        sizeBtns[i].classList.add("act-toggle");
        
        selectBtn.onclick = () =>{
            if(sizeBtns[i].style.backgroundColor = "black"){
                change();
            }
        }
    }
}



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
const detailInfoGroup = document.querySelector(".detail-info-group "); 

detailInfoBtn.onclick = () => {
    detailInner();
}

function detailInner() {
    detailInfoGroup.style.borderBottom = "1px solid black"
    detailInfo.innerHTML = `
        <p>억스(AAUXX)의 아이링(iRing)은 스마트 디바이스의 그립과 거치를 편리하게 해주는 디지털 액세서리 브랜드입니다. </p>
        <p>끊임없이 테크놀로지 트렌드를 파악하고 더 편안한 디지털 라이프를 위해 고민하고 제안합니다.</p>  
        <p>아이링(iRing)은 특허 기술을 기반으로 타협하지 않는 품질의 제품을 제공하기 위해 노력합니다.</p>
        
        <span>* 반드시 아이폰 12 이상 맥세이프가 탑재된 기종 또는 맥세이프 케이스와 함께 사용하세요.</span>
`;
}
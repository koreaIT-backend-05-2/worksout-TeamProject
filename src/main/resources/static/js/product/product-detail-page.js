const sizeBtns = document.querySelectorAll(".size-button");
const selectBtn = document.querySelector(".select-button");

 let productSize = null;


// 사이즈 클릭시만 구매 또는 장바구니 버튼 생성

selectBtn.onclick = () => {
    alert("사이즈를 선택해주세요");
}

for(let i = 0; i < sizeBtns.length; i++) {
    sizeBtns[i].onclick = () => {
	
		productSize = sizeBtns[i].value;
	
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
                
                const cartBtn = document.querySelector(".cart-button");

				cartBtn.onclick = () => {
					cartLoad();
				}   
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

/** >>>>>>>>>>>>>>>>>>>>>REQ>>>>>>>>>>>>>>>>>>>>>> */

let productCode = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);

load();

console.log("urlNumber: " + productCode);

function load() {
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/product/" + productCode,
		dataType: "json",
		success: (response) => {
			getProduct(response.data)
		},
		error: errorMessage
		
	});
}

function getProduct(product) {
	const productBrand = document.querySelector(".product-brand");
	const productName = document.querySelector(".product-name");
	const productDetailName = document.querySelector(".product-detail-name");
	const productKorName = document.querySelector(".product-kor-name");
	const productCodeNum = document.querySelector(".product-code");
	const productPrice = document.querySelector(".product-price");
	const productInfo = document.querySelector(".product-info");
	const productImg = document.querySelector(".img-group");
	
	productCode = product.productCode;
	
	productBrand.innerHTML = product.productBrand;
	productName.innerHTML = product.productName;
	productDetailName.innerHTML = product.productDetailName;
	productKorName.innerHTML = product.productKorName;
	productCodeNum.innerHTML = productCode;
	productPrice.innerHTML = product.productPrice + "원";
	
	let productFileArray = new Array();
	
	product.productImgFiles.forEach(file => {
		if(file.fileCode != undefined) {
			productFileArray.push(`
			 <img src = "/image/product/${file.fileName}">
			`)
		}
	});
	
	productImg.innerHTML = productFileArray;
	
	productInfo.innerHTML = product.productInfo;
}



//장바구니

function cartLoad() {
	
	
	
	let addCart = {
		username: user.user_id,
		"productCode": productCode,
		"productSize" : productSize
	}
	

	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/cart/addCart",
		contentType: "application/json",
		data:JSON.stringify(addCart),
		success: (response) => {
			if(response.data) {
				alert("장바구니 추가완료")
			}
		},
		error: errorMessage
		
	})
}


//에러 메시지
function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}


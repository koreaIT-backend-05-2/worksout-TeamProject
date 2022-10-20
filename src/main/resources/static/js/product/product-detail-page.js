const selectBtn = document.querySelector(".select-button");

let productGroup = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);

	console.log("urlNumber: " + productGroup);
// 사이즈 클릭시만 구매 또는 장바구니 버튼 생성

selectBtn.onclick = () => {
    alert("사이즈를 선택해주세요");
}

load();

function buttonEvent(product){
		let sizeBtns = document.querySelectorAll(".size-button");
		
		 let productSize = new Array();
		 
		 let productCode = new Array();
		
		//사이즈 선택 이벤트
	for(let i = 0; i < sizeBtns.length; i++) {
	    sizeBtns[i].onclick = () => {
		
			productSize = sizeBtns[i].textContent;
			console.log("뭘까 이건: " + productSize)
			
			productCode = sizeBtns[i].value;
			console.log("뭘까 이건2: " + productCode)
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
						if(user == null) {
								alert("로그인이 필요합니다.")
								location.href = "/signin"
						}
						cartLoad(productSize, productCode);
					}   
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

buttonEvent();

/** >>>>>>>>>>>>>>>>>>>>>REQ>>>>>>>>>>>>>>>>>>>>>> */


function load() {
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/product/" + productGroup,
		dataType: "json",
		success: (response) => {
			const product = response.data;
			
			getProduct(response.data)
			buttonEvent(product)
			
		},
		error: errorMessage
		
	});
}

function getProduct(product) {
	const productBrand = document.querySelector(".product-brand");
	const productName = document.querySelector(".product-name");
	const productDetailName = document.querySelector(".product-detail-name");
	const productKorName = document.querySelector(".product-kor-name");
	const productgroupNum = document.querySelector(".product-code");
	const productPrice = document.querySelector(".product-price");
	const productInfo = document.querySelector(".product-info");
	const productImg = document.querySelector(".img-group");
	const sizeBtnsGroup = document.querySelector(".size-buttons-group");
	
	console.log("상품코드: " + product.productSizeList.length)
	
	productGroup = product.productGroup;
	
	productBrand.innerHTML = product.productBrand;
	productName.innerHTML = product.productName;
	productDetailName.innerHTML = product.productDetailName;
	productKorName.innerHTML = product.productKorName;
	productgroupNum.innerHTML = productGroup;
	productPrice.innerHTML = product.productPrice + "원";
	
	for(let i = 0; i > product.productSizeList.length; i++) {
		const lastProduct = document.querySelector(".last-product");

		console.log("lastProduct: " + lastProduct)
		if(product.productSizeList[i].size_amount == 1) {
				lastProduct.innerHTML += "해당 상품은 품절입니다";
			}
	}


product.productSizeList.forEach(size => {
	sizeBtnsGroup.innerHTML += `
	<button type="button" class="size-button" id="product-size-${size.product_code}" value ="${size.product_code}">${size.size_name}</button>
	`;
	
})
	
	let productFileArray = new Array();
	
	product.productFileList.forEach(file => {
		if(file.file_code != undefined) {
			productFileArray.push(`
			 <img src = "/image/product/${file.file_name}">
			`)
		}
	});
	
	productImg.innerHTML = productFileArray;
	
	productInfo.innerHTML = product.productInfo;
	
//	let productCodeArray = new Array();
//	
//	product.productSizeList.forEach(size => {
//		productCodeArray.push(size.product_code);
//	})
//	
//	console.log("제발 코드: " + productCodeArray)

	
//	let count = 0;
//	let allCount = 0;
//	let name = null;
//	for(let i = 0 ; i < product.productSizeList.length; i++ ){
//		if(name != product.productFileList[i].file_name) {
//		count++; //다른사진갯수
//		}
//		allCount++;	//전제사진갯수
//		name = product.productFileList[i].file_name;
//	}
//	console.log("asdsadsa"+count);
//	console.log("allCount"+allCount);
//	console.log("필요한 갯수 = " + (count / allCount));
//	
//		let resultNum = allCount / count;
//	
//		if (resultNum < 1 ) {
//			resultNum = 1;
//		}
//	
//		for(let i = 0; i < resultNum ; i ++){
//		const sizeBtnsGroup = document.querySelector(".size-buttons-group");
//	
//		sizeBtnsGroup.innerHTML += `
//					<button type="button" class="size-button" id="product-size-${product.productSizeList[i].size_name}" value ="">${product.productSizeList[i].size_name}</button>
//			`;
//	}
	
	
}

	

//장바구니

function cartLoad(productSize, productCode) {
	let productPrice = document.querySelector(".product-price");
	
	cartPrice =parseInt(productPrice.textContent);
	
	console.log(parseInt(cartPrice))
	
	let addCart = {
			userCode: user.user_code,
			"productCode": productCode,
			"productGroup": productGroup,
			"productSize": productSize,
			"cartPrice": cartPrice,
			"cartAmount": 1 
		}
		
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/cart/addCart",
		contentType: "application/json",
		data:JSON.stringify(addCart),
		success: (response) => {
				
				alert("장바구니 추가완료")
				alert(JSON.stringify(addCart))
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


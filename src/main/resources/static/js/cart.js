const productPrice = document.querySelector(".td-price");

let userCode = user.user_code;

let totalPrice = 0;

//totalPrice.toLocaleString("ko-kr")

checkUser();

loadPaymentpage();

load();

console.log("userCode: " + userCode);


//로그인 했을 시 비회원 구매 사라짐
function checkUser() {
	if(user != null) {
		const nonmemberPurchaseBtn = document.querySelector(".nonmember-purchase-button");
		nonmemberPurchaseBtn.style.display = "none";
	}
}

function loadPaymentpage() {
	const memberPurchaseBtn = document.querySelector(".member-purchase-button");
	
	memberPurchaseBtn.onclick = () => {
		location.href = "/payment"
	}
	
}

function load() {
	
	$.ajax({
		async: false,
		type: "get",
		url: "api/v1/cart/" + userCode,
		dataType: "json",
		success: (response)  => {
			getCartList(response.data)
			totalPriceEve(response.data)
			cartCheckbox(response.data)
		},
		error: errorMessage
		
	});
	
}


function getCartList(list) {
	const cartTbody = document.querySelector("tbody");
	
	console.log(list[0].cartProductFileName)
	
	list.forEach((cart, idx) => {
		let productHiddenPrice = cart.cartProductPrice

		let productPrice = cart.cartProductPrice.toLocaleString("ko-kr")
		
		
		cartTbody.innerHTML += `
		<tr class="cart-product-list-${cart.cartCode}">
            <td class="td-checkbox"><input class="cart-flag-checkbox" type="checkbox"  value="${cart.cartCode}" checked = "${cart.payFlag}"></td>
            <td class="td-items-info">
               <div><img src="/image/product/${cart.cartProductFileName}" onClick="location.href = '/product/${cart.productGroup}'" alt="#"></div>
                <div class="items-info-content"> 
                <p class="cartCode-hidden cartCode-${idx}">${cart.cartCode}</p>
                  <p>${cart.cartProductName}</p> 
                   <p>${cart.cartProductBrand}</p> 
                     <p>사이즈: ${cart.cartProductSize}</p> 
               </div>
            </td>
            <td>
                <div class="td-quantity-button">
                    <button type="button"  class="minus minus-${idx}">-</button>
                    <p class="amount-${idx}">${cart.cartProductAmount}</p>
                    <button type="button" class="plus plus-${idx}">+</button>
                </div>
            </td>
            <td class="td-price-hidden-${idx}" style="display:none">${productHiddenPrice}원</td>
            <td class="td-price-${idx}">${productPrice}원</td>
            <td class="td-trash"><i class="fa-solid fa-trash-can delete-cart-${idx}"></i></td>
        </tr>
		`
	});
	
	console.log("<<<<<<<<: " + list[0].cartCode)
	
	increaseAmount();
	
	deleteClickEve();
	
}

//수량 증가

function increaseAmount() {
	
	const minus = document.querySelectorAll(".minus");
	const plus = document.querySelectorAll(".plus");

	let amount = null;
	
	let arr = new Array();
	
	for(let i = 0; i < plus.length; i++) {
		let amountText = document.querySelector(`.amount-${i}`);
		let priceText = document.querySelector(`.td-price-${i}`);
		let priceHiddenText = document.querySelector(`.td-price-hidden-${i}`);
		
		let price = priceHiddenText.textContent;
		
		arr.push(amountText.className.slice(-1));
		
//		amountText.forEach(amount => {
//			arr.push(amount.className.slice(-1))
//		});
		
		console.log("i: " + i)
		console.log("arr: " + arr);
//		console.log(priceText.textContent)
		
		
		console.log("plus 크기 : " + plus.length)
		plus[i].onclick = (e) => {
			
		    if(amountText.textContent != 99) {
	//			let totalAmount =  amountText[i].innerHTML;
				amount = parseInt(amountText.textContent);
		        amountText.textContent = amount + 1;
		        priceHiddenText = parseInt(price) *  (amount + 1) + "원"
		        console.log(amount)
		        console.log(price)
		        console.log("i의 값: " + i)
		        
		        let cartCodeIndex = e.target.classList[1].substring(e.target.classList[1].lastIndexOf("-") + 1);
		        
				const cartCode = document.querySelector(`.cartCode-${cartCodeIndex}`).textContent;
				
				updateLoad(cartCode,  amountText.textContent,  priceHiddenText);
				
				plusTotalPrice(parseInt(price))
		    }
		}
	}
	
	for(let i = 0; i < minus.length; i++) {
		let amountText = document.querySelector(`.amount-${i}`);
		let priceText = document.querySelector(`.td-price-${i}`);
		let priceHiddenText = document.querySelector(`.td-price-hidden-${i}`);
		
		let price = priceHiddenText.textContent;
		
		console.log(price)
		console.log(price)
		
		minus[i].onclick = (e) => {
		    if(amountText.textContent == 1) {
		        amountText.textContent = 1;
		        
		    }else {
				amount = parseInt(amountText.textContent);
		        amountText.textContent = amount - 1;
		        priceHiddenText = parseInt(price) *  (amount - 1) + "원"
		        
		        let cartCodeIndex = e.target.classList[1].substring(e.target.classList[1].lastIndexOf("-") + 1);
		        
				const cartCode = document.querySelector(`.cartCode-${cartCodeIndex}`).textContent;
				
				console.log(priceText)
				console.log(priceHiddenText)
				
				updateLoad(cartCode, amountText.textContent, priceHiddenText);
				
				minusTotalPrice(parseInt(price))
		    }
		}
	}
}

function deleteClickEve() {
	const deleteBtns = document.querySelectorAll(".td-trash i");
	
	for(let i = 0; i < deleteBtns.length; i++) {
			deleteBtns[i].onclick = () => {
				let deletePriceText = document.querySelector(`.td-price-${i}`);
				let priceHiddenText = document.querySelector(`.td-price-hidden-${i}`);
				let amountText = document.querySelector(`.amount-${i}`);
				
				 deletePrice = parseInt(priceHiddenText.textContent);
				 amount = parseInt(amountText.textContent);
				 
				 let deletePriceWithAmount = deletePrice * amount;
				 
				 console.log("제발 되야한다: " + deletePriceWithAmount)
				 
				 deleteTotalPrice(deletePriceWithAmount);
				
				let deleteIndex = deleteBtns[i].className.slice(-1)

				const cartCode = document.querySelector(`.cartCode-${deleteIndex}`).textContent;
				console.log(cartCode)

				deleteCart(cartCode);

				const cartProductList = document.querySelector(`.cart-product-list-${cartCode}`);
			
				cartProductList.remove();
				
				
		}
	}
}


//총 주문금액 계산

function totalPriceEve(ttPrice) {
	const totalOrderPrice = document.querySelector(".total-order-price");
	const totalPaymentPrice = document.querySelector(".total-payment-price");

	ttPrice.forEach(price => {
		totalPrice += (price.cartProductPrice) * (price.cartProductAmount)	
	});
	
	console.log(totalPrice);
	
//	totalOrderPrice.textContent = totalPrice + "원"
//	totalPaymentPrice.textContent = totalPrice + "원"
	
	let localeTotalPrice = totalPrice.toLocaleString("ko-kr")
	
	totalOrderPrice.textContent = localeTotalPrice + "원"
	totalPaymentPrice.textContent = localeTotalPrice + "원"
		

//	const totalOrderPrice = document.querySelector(".total-order-price");
//	const totalPaymentPrice = document.querySelector(".total-payment-price");
//	
//	let currentPrice = price * amount;
//	
//	totalOrderPrice.textContent = currentPrice + "원"
//	totalPaymentPrice.textContent = currentPrice + "원"
//	
//	
//	let totalPrice = 0;
//	
//	totalPrice += price * amount
//		
//	
//	console.log(totalPrice);
//	
//	totalOrderPrice.textContent = totalPrice + "원"
//	totalPaymentPrice.textContent = totalPrice + "원"
	
}

function plusTotalPrice(price) {
	const totalOrderPrice = document.querySelector(".total-order-price");
	const totalPaymentPrice = document.querySelector(".total-payment-price");
	
	totalPrice +=  price;
	
	console.log(totalPrice)

//	totalOrderPrice.textContent = totalPrice + "원"
//	totalPaymentPrice.textContent = totalPrice + "원"
	
		
	let localeTotalPrice = totalPrice.toLocaleString("ko-kr")
	
	totalOrderPrice.textContent = localeTotalPrice + "원"
	totalPaymentPrice.textContent = localeTotalPrice + "원"
	
}

function minusTotalPrice(price) {
	const totalOrderPrice = document.querySelector(".total-order-price");
	const totalPaymentPrice = document.querySelector(".total-payment-price");
	
	console.log(price);
	
	totalPrice -= price;
	
	console.log(totalPrice)
	
//	totalOrderPrice.textContent = totalPrice + "원"
//	totalPaymentPrice.textContent = totalPrice + "원"
	
	let localeTotalPrice = totalPrice.toLocaleString("ko-kr")
	
	totalOrderPrice.textContent = localeTotalPrice + "원"
	totalPaymentPrice.textContent = localeTotalPrice + "원"
	
//	console.log(typeof(totalOrderPrice.textContent))
	
}


function deleteTotalPrice(price) {
	const totalOrderPrice = document.querySelector(".total-order-price");
	const totalPaymentPrice = document.querySelector(".total-payment-price");

	console.log("내가 원하는 값: " + price)
	
	console.log("현재 금액: " + totalPrice)
	
	totalPrice -= price;
	
//	totalOrderPrice.textContent = totalPrice + "원"
//	totalPaymentPrice.textContent = totalPrice + "원"
	
	let localeTotalPrice = totalPrice.toLocaleString("ko-kr")
	
	totalOrderPrice.textContent = localeTotalPrice + "원"
	totalPaymentPrice.textContent = localeTotalPrice + "원"
	
	console.log("ttttttttttttttttprce: " + totalPrice)
		
}


//function purchaseCancelFlag() {
	
//	
//	
//	
//}



function cartCheckbox(data) {
	const cartAllCheckbox = document.querySelector(".cart-all-checkbox");
	const cartFlagCheckbox = document.querySelectorAll(".cart-flag-checkbox");
	const cartCodes = document.querySelector(`.cartCode-hidden`);
	

	let cartCode = cartCodes.textContent;
	
	console.log(cartCodes.textContent);
	console.log(cartFlagCheckbox)	
	
	
	let flagArr = new Array();
	
	data.forEach(flag => {
		flagArr.push(flag.payFlag)
	}) 
	
	console.log(flagArr)
	console.log(cartAllCheckbox.checked)
	
	for(let i = 0; i < flagArr.length; i++) {
		cartFlagCheckbox[i].onchange = () => {
			if(cartFlagCheckbox[i].checked == false) {
				cartAllCheckbox.checked = false;
			}else {
				cartAllCheckbox.checked = true;
			}
		}
	}
	for(let i = 0; i < flagArr.length; i++) {
		let amountText = document.querySelector(`.amount-${i}`);
		let priceText = document.querySelector(`.td-price-${i}`);
		let priceHiddenText = document.querySelector(`.td-price-hidden-${i}`);
		
		console.log(amountText)
		console.log(priceText)
		console.log(priceHiddenText)
		
		console.log(typeof(amountText.textContent)) //string
		console.log(typeof(priceText.textContent)) //string
		console.log(typeof(priceHiddenText.textContent)) //string
		
		if(cartFlagCheckbox[i].checked == false) {
			
		} 
		
		
	}
		
	
	
	cartAllCheckbox.onchange = () => {
	
		if(cartAllCheckbox.checked == true) {
			for(let i = 0; i < flagArr.length; i++) {
				flagArr[i] = true;
				cartFlagCheckbox[i].checked = true
				
			}
		}else {
			for(let i = 0; i < flagArr.length; i++) {
				flagArr[i] = false;
				cartFlagCheckbox[i].checked = false;
				
			}
		}
	}
	
//	 updateCartCheck(cartCode);
}


function updateLoad(cartCode, amount, price) {
console.log("cartCode: " + cartCode)
console.log("amount" + amount)
console.log("price" + price)

	let updateData = {
		"cartCode": parseInt(cartCode),
		cartProductAmount: amount,
		cartProductPrice: parseInt(price)
	}

	$.ajax({
		async: false,
		type: "put",
		url: `/api/v1/cart/modify/` + cartCode,
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(updateData),
		success: (response) => {
			console.log(response.data)
		},
		error: errorMessage
		
	});

//	console.log("cartCode: " + cartCode)
//	console.log("productCode: " + productCode)
//	console.log("productGroup: " + productGroup)
//	console.log("cartProductPrice" + cartProductPrice)
//	console.log("cartProductAmount" + cartProductAmount)
//	
	
}


function deleteCart(cartCode) {
	
	$.ajax({
		async: false,
		type: "delete",
		url: `/api/v1/cart/remove/` + cartCode,
		dataType: "json",
		success: (response) => {
			console.log(response.data)
		}
	});
}

//function updateCartCheck(cartCode) {
//	
//	console.log(cartCode)
//	
//	$.ajax({
//		async: false,
//		type: "put",
//		url: "/api/v1/cart/flag/" + cartCode,
//		dataType: "json",
//		success: (response) => {
//			
//		},
//		error: errorMessage
//		
//	});
//}

 
//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}


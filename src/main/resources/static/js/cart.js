const productPrice = document.querySelector(".td-price");

let userCode = user.user_code;

let totalPrice = 0;

checkUser();

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
			
		},
		error: errorMessage
		
	});
	
}


function getCartList(list) {
	const cartTbody = document.querySelector("tbody");
	
	console.log(list[0].cartProductFileName)
	
	list.forEach((cart, idx) => {
		cartTbody.innerHTML += `
		<tr class="cart-product-list-${cart.cartCode}">
            <td class="td-checkbox"><input type="checkbox"></td>
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
            <td class="td-price-${idx}">${cart.cartProductPrice}원</td>
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
		
		let price = priceText.textContent;
		
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
		        priceText =parseInt(price) *  (amount + 1) + "원"
		        console.log(amount)
		        console.log(price)
		        console.log("i의 값: " + i)
		        
		        let cartCodeIndex = e.target.classList[1].substring(e.target.classList[1].lastIndexOf("-") + 1);
		        
				const cartCode = document.querySelector(`.cartCode-${cartCodeIndex}`).textContent;
				
				updateLoad(cartCode,  amountText.textContent,  priceText);
				
				plusTotalprice(parseInt(price))
		    }
		}
	}
	
	for(let i = 0; i < minus.length; i++) {
		let amountText = document.querySelector(`.amount-${i}`);
		let priceText = document.querySelector(`.td-price-${i}`);
		
		let price = priceText.textContent;
		
		minus[i].onclick = (e) => {
		    if(amountText.textContent == 1) {
		        amountText.textContent = 1;
		        
		    }else {
				amount = parseInt(amountText.textContent);
		        amountText.textContent = amount - 1;
		        priceText =parseInt(price) *  (amount - 1) + "원"
		        
		        let cartCodeIndex = e.target.classList[1].substring(e.target.classList[1].lastIndexOf("-") + 1);
		        
				const cartCode = document.querySelector(`.cartCode-${cartCodeIndex}`).textContent;
				
				updateLoad(cartCode, amountText.textContent, priceText);
				
				minusTotalprice(parseInt(price))
		    }
		}
	}
}

function deleteClickEve() {
	const deleteBtns = document.querySelectorAll(".td-trash i");
	
	for(let i = 0; i < deleteBtns.length; i++) {
			deleteBtns[i].onclick = () => {
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
	
	totalOrderPrice.textContent = totalPrice + "원"
	totalPaymentPrice.textContent = totalPrice + "원"
		

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

function plusTotalprice(price) {
	const totalOrderPrice = document.querySelector(".total-order-price");
	const totalPaymentPrice = document.querySelector(".total-payment-price");
	
	
	totalPrice +=  price;
	
	console.log(totalPrice)
	
	totalOrderPrice.textContent = totalPrice + "원"
	totalPaymentPrice.textContent = totalPrice + "원"
	
}

function minusTotalprice(price) {
	const totalOrderPrice = document.querySelector(".total-order-price");
	const totalPaymentPrice = document.querySelector(".total-payment-price");
	
	
	totalPrice -=  price;
	
	console.log(totalPrice)
	
	totalOrderPrice.textContent = totalPrice + "원"
	totalPaymentPrice.textContent = totalPrice + "원"
	
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

 
//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}


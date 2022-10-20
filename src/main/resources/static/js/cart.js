const productPrice = document.querySelector(".td-price");

let userCode = user.user_code;

load();

console.log("userCode: " + userCode);



function load() {
	
	$.ajax({
		async: false,
		type: "get",
		url: "api/v1/cart/" + userCode,
		dataType: "json",
		success: (response)  => {
			getCartList(response.data)
		},
		error: errorMessage
		
	});
	
}


function getCartList(list) {
	const cartTbody = document.querySelector("tbody");
	
	console.log(list[0].cartProductFileName)
	
	list.forEach((cart, idx) => {
		cartTbody.innerHTML += `
		<tr>
            <td class="td-checkbox"><input type="checkbox"></td>
            <td class="td-items-info">
               <div><img src="/image/product/${cart.cartProductFileName}" alt=""></div>
                <div class="items-info-content"> 
                  <p>${cart.cartProductName}</p> 
                   <p>${cart.cartProductBrand}</p> 
                     <p>사이즈: ${cart.cartProductSize}</p> 
               </div>
            </td>
            <td>
                <div class="td-quantity-button">
                    <button type="button"  class="minus">-</button>
                    <p class="amount-${idx}">${cart.cartProductAmount}</p>
                    <button type="button" class="plus">+</button>
                </div>
            </td>
            <td class="td-price-${idx}">${cart.cartProductPrice}원</td>
            <td class="td-trash"><i class="fa-solid fa-trash-can"></i></td>
        </tr>
		`
		
		
	});
	
	increaseAmount();
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
		plus[i].onclick = () => {
		    if(amountText.textContent != 99 && arr[i] == i) {
	//			let totalAmount =  amountText[i].innerHTML;
				amount = parseInt(amountText.textContent);
		        amountText.textContent = amount + 1;
		        priceText.textContent =parseInt(price) *  (amount + 1) + "원"
		        console.log(amount)
		        console.log(price)
		    }
		}
	}
	
	for(let i = 0; i < minus.length; i++) {
		let amountText = document.querySelector(`.amount-${i}`);
		let priceText = document.querySelector(`.td-price-${i}`);
		
		let price = priceText.textContent;
		
		minus[i].onclick = () => {
		    if(amountText.textContent == 1) {
		        amountText.textContent = 1;
		    }else {
				amount = parseInt(amountText.textContent);
		        amountText.textContent = amount - 1;
		        priceText.textContent =parseInt(price) *  (amount - 1) + "원"
		    }
		}
	}
	
	
}


//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}


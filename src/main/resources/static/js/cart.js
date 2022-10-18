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
                    <p class="amount amount_${idx}">${cart.cartProductAmount}</p>
                    <button type="button" class="plus">+</button>
                </div>
            </td>
            <td class="td-price">${cart.cartProductPrice}원</td>
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
	let price = document.querySelectorAll(".td-price");
	
	
	let priceArray = new Array();
	
	for(let i = 0; i < price.length; i++) {
		console.log("price: " + price[i].textContent);
		priceArray = price[i].textContent
	}
	
	let amount = 1;
	
	for(let i = 0; i < plus.length; i++) {
		let amountText = document.querySelectorAll(`.amount_${i}`);
		
		console.log(">>>>>>>>>>>>: " + amountText)
		
		console.log("plus 크기 : " + plus.length)
		plus[i].onclick = () => {
		    if(amountText[i].textContent != 99) {
	//			let totalAmount =  amountText[i].innerHTML;
		        amountText[i].textContent = ++amount;
		        
		    }
		}
	}
	
	for(let i = 0; i < minus.length; i++) {
		let amountText = document.querySelectorAll(`.amount_${i}`);
		
		minus[i].onclick = () => {
		    if(amountText[i].textContent == 1) {
		        amountText[i].textContent = amount;
		    }else {
		        amountText[i].textContent = --amount;
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


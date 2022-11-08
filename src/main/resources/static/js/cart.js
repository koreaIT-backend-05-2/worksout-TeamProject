//const minus = document.querySelector(".minus");
//const plus = document.querySelector(".plus");
//const amountText = document.querySelector(".amount");
//const productPrice = document.querySelector(".td-price");
//
//console.log(plus);
//
//let amount = 1;
//
//let price = 219900;
//
//plus.onclick = () => {
//    if(amountText.innerHTML != 15) {
//        amountText.innerHTML = ++amount;
//        productPrice.innerHTML = ++price;
//    }
//}
//
//minus.onclick = () => {
//    if(amountText.innerHTML == 1) {
//        amountText.innerHTML = amount;
//    }else {
//        amountText.innerHTML = --amount;
//    }
//}

const tBody = document.querySelector("#tbody-area");


tBody.innerHTML = "";

load();


function load() {
	$.ajax({
		async:false,
		type: "get",
		url: "/api/v1/cart/getCart",
		data:{
			"username": user.user_code
		},
		datatype: "json",
		success:(response) => {
			if(response.data){
				//console.log(response.data);
				getList(response.data);
			}
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function getList(cartList){
	
	let cartAmount = new Array();
	let productPrice = new Array();
	
	cartList.forEach(cart => {
		
		cartAmount.push(cart.cartAmount);
		productPrice.push(cart.productPrice);
		let varAmount = parseInt(cart.cartAmount);
		
		tBody.innerHTML += `
			<tr class="tr-cart-items">
                <td class="td-checkbox"><input type="checkbox"></td>
                <td class="td-items-info">
                    <div><img src="/image/product/${cart.productImg.fileName}" alt=""></div>
                    <div class="items-info-content">
                        <p>${cart.productName}</p>
                        <p>${cart.productDetailName}</p>
                        <p>사이즈: ${cart.productSize}</p>
                    </div>
                </td>
                <td class="td-amount" id = "ct-${cart.cartAmount}">
                    <div class="td-quantity-button">
                        <button type="button" class="minus">-</button>
                        <p class = "amount">${varAmount}</p>
                        <button type="button" class="plus">+</button>
                    </div>
                </td>
                <td class="td-price" id = "ct-${cart.cartCode}">${cart.productPrice}원</td>
                <td class="td-trash"><i class="fa-solid fa-trash-can"></i></td>
            </tr>
					
		`;
	})
	console.log(cartAmount);
	console.log(productPrice);
	addEvent();
}

function addEvent(){
	const trCartItems = document.querySelectorAll(".tr-cart-items");
	for(let trCItem of trCartItems){
		const cartCode = subStringProductCode(trCItem);
		const cartAmount = subStringCartAmount(trCItem);
		mouseEvent(trCItem, cartCode, cartAmount);
	}
}

function subStringProductCode(trCItem){
	//console.log(items);
	
	const itemSelection = trCItem.querySelector(".td-price");
	console.log("itemSelection : " + itemSelection);
	
	const cartCode = itemSelection.getAttribute("id");
	console.log("productGroup : " + cartCode);
	
	const tokenIndex = cartCode.lastIndexOf("-");
	console.log("tokenIndex : " + tokenIndex);
	
	console.log("cartCode.substring(tokenIndex + 1) : " + cartCode.substring(tokenIndex + 1));
	console.log("  ");
	return cartCode.substring(tokenIndex + 1);
}

function subStringCartAmount(trCItem){
	//console.log(items);
	
	const itemSelection = trCItem.querySelector(".td-amount");
	console.log("itemSelection : " + itemSelection);
	
	const cartAmount = itemSelection.getAttribute("id");
	console.log("productGroup : " + cartAmount);
	
	const tokenIndex = cartAmount.lastIndexOf("-");
	console.log("tokenIndex : " + tokenIndex);
	
	console.log("cartAmount.substring(tokenIndex + 1) : " + cartAmount.substring(tokenIndex + 1));
	console.log("  ");
	return cartAmount.substring(tokenIndex + 1);
}

function mouseEvent(trCItem, cartCode, cartAmount){
	const plsBtn = trCItem.querySelector(".plus");
	const mnsBtn = trCItem.querySelector(".minus");
	console.log(trCItem);
	console.log("mouseEvent >> " +cartAmount);
	let varAmount = parseInt(cartAmount);
	
	const amountValue = trCItem.querySelector(".amount");
	plsBtn.onclick=()=>{
		console.log("plus cartCode: " + cartCode);
		update(cartCode, varAmount++);

		amountValue.innerText = ``;
		amountValue.innerText = `${varAmount}`;		
		
	}
	mnsBtn.onclick=()=>{
		console.log("minus cartCode: " + cartCode);
		if(varAmount > 1 ) {
			varAmount--;	
		}		
		update(cartCode, varAmount);

		amountValue.innerText = ``;
		amountValue.innerText = `${varAmount}`;		
	}
}

function update(cartCode, num) {
	console.log("cartCode : "+ cartCode + "  num: " + num);
	$.ajax({
		type:"put",
		url: `/api/v1/cart/setCart/${cartCode}`,
		contentType: "application/json",
		data: JSON.stringify({
			"cartCode": cartCode,
			"cartAmount" : num
		}),
		async: false,
		dataType: "JSON",
		success: (response) => {
			console.log(response.data);
			//location.replace(location.href);
			
		},
		error: (error) => {
			console.log(error);
		}
	})
}
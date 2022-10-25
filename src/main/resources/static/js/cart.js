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
                <td>
                    <div class="td-quantity-button">
                        <button type="button" class="minus">-</button>
                        <p>${cart.cartAmount}</p>
                        <button type="button" class="plus">+</button>
                    </div>
                </td>
                <td class="td-price" id = "ct-${cart.productCode}">${cart.productPrice}원</td>
                <td class="td-trash"><i class="fa-solid fa-trash-can"></i></td>
            </tr>
					
		`;
	})
	console.log(cartAmount);
	console.log(productPrice);
	addEvent();
}

function addEvent(cartList){
	const trCartItems = document.querySelectorAll(".tr-cart-items");
	for(let trCItem of trCartItems){
		const productCode = subStringProductCode(trCItem);
		
		mouseEvent(trCItem, productCode);
	}
}

function subStringProductCode(trCItem){
	//console.log(items);
	
	const itemSelection = trCItem.querySelector(".td-price");
	console.log("itemSelection : " + itemSelection);
	
	const productCode = itemSelection.getAttribute("id");
	console.log("productGroup : " + productCode);
	
	const tokenIndex = productCode.lastIndexOf("-");
	console.log("tokenIndex : " + tokenIndex);
	
	console.log("productCode.substring(tokenIndex + 1) : " + productCode.substring(tokenIndex + 1));
	console.log("  ");
	return productCode.substring(tokenIndex + 1);
}

function mouseEvent(trCItem, productCode){
	const plsBtn = trCItem.querySelector(".plus");
	const mnsBtn = trCItem.querySelector(".minus");
	console.log(trCItem);
	plsBtn.onclick=()=>{
		console.log("plus " + productCode);
		
	}
	mnsBtn.onclick=()=>{
		console.log("minus " + productCode);		
		
	}
}
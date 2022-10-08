loadMainItems();

function loadMainItems() {
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/admin/list/" + 1,
		data: {
			"searchFlag": null,
			"searchValue": null
		},
		dataType: "json",
		success:(response) => {
			getMainItems(response.data);
		},
		error:(error) => {
			console.log(error);
		}
		
	})
}

function getMainItems(list) {
	//console.log(list);

	const mainContainer = document.querySelector(".main-container");
	let arr = new Array();
	
	mainContainer.innerHTML = "";
	
	for(let i = 0; i < list.length ; i++){
		if(list[i].productGender == "MainImage") arr.push(i);
	}
	//console.log(arr);
	
	for(let i = 0 ; i < 2 ; i++){
		//console.log(list[arr[i]]);
		mainContainer.innerHTML += `
			<div class="a products">
			<div class = "mainItems" id = "pd-${(i+1)*100}">
                <div>
                    <img src="/image/main/${list[arr[i]].files[0].fileName}" class="first-img">
                </div>
                <h1>
                    <div>
                        <p>${list[arr[i]].productName}</p>
                    </div>
                </h1>
                </div>
            </div>
		`;
	}

	let count = 0;
	let item = null;
	
	list.forEach(product => {
		if(item != product.productName && count < 14){
			mainContainer.innerHTML += `
				<div class="a products">
	                <div class = "mainItems" id = "pd-${product.productGroup}">
		                <div>
		                    <img src="/image/product/${product.files[0].fileName}" class="first-img">
		                </div>
		                <h1>
		                    <div>
		                        <p>${product.productName}</p>
		                    </div>
		                </h1>
	                </div>
	            </div>
			`;

//			mainContainer.innerHTML += `<img src="/image/product/${product.files[0].fileName}" class="first-img">`;
		}else{
			return false;
		}
		item = product.productName;
		count++;
	});
	addEvent(list);
}

function subStringProductGroup(items){
	//console.log(items);
	
	const itemSelection = items.querySelector(".mainItems");
	//console.log("itemSelection : " + itemSelection);
	
	const productGroup = itemSelection.getAttribute("id");
	//console.log("productCode : " + productCode);
	
	const tokenIndex = productGroup.lastIndexOf("-");
	//console.log("tokenIndex : " + tokenIndex);
	
	//console.log("productCode.substring(tokenIndex + 1) : " + productCode.substring(tokenIndex + 1));
	//console.log("  ");
	return productGroup.substring(tokenIndex + 1);
}

function addEvent(list){
	const products = document.querySelectorAll(".products");
	//console.log(list);
	
	for(let items of products) {
		console.log(items);
		
 		const productGroup = subStringProductGroup(items);
		//console.log(productGroup);
 		
		mouseEvent(items, productGroup);
	}
}

function mouseEvent(items, productGroup){
	const itemSelection = items.querySelector(".mainItems");
	//console.log(defaultDiv);
	//console.log(productGroup);
	itemSelection.onclick = () => {
		if(productGroup < 100){
			location.href = "/product/" + productGroup;			
		}else if(productGroup >= 100){
			location.href = "/event/" + productGroup/100;					
		}
	}
	
	itemSelection.onmouseover = () => {
		console.log("over + " + productGroup);
	}
//	itemSelection.onmouseout = () => {
//		console.log("out + " + productGroup);
//	}
}
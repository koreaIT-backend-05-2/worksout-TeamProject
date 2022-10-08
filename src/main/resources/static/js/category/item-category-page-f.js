const filterButton = document.querySelector(".filter-button");
const filter = document.querySelector(".filters");

let genderSelect = "f";
const type = 1;

load(genderSelect);
 

filterButton.onclick = () => {
   filter.classList.toggle("hidden");
}

function load(genderSelect){
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/admin/list/" + type,
		data:{
			"searchFlag" : null,
			"searchValue": null
		},
		datatype: "json",
		success:(response) => {
			//console.log(response.data);
			loadItems(response.data, genderSelect);
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function loadItems(list, genderSelect) {
	const loadedItems = document.querySelector(".loaded-items");
	loadedItems.innerHTML = "";
	
	let item = null;
	
	
	list.forEach(product => {
		
		// 상품코드 중복확인 + 젠더
		if(item != product.productName && product.productGender == genderSelect) {
//		if(item != product.productName ) {
			console.log(product);
			let price = product.productPrice / 1000;
			
			let imgFiles = new Array();
			
			let i = 0;
			
			for(i = 0 ; i < product.files.length ; i++){
				imgFiles = product.files[i].fileName;
				//console.log("productCode = "+ product.productCode);
				//console.log("imgFiles = " + imgFiles);
			}
			
			
			loadedItems.innerHTML += `
				<div class = "item-selection" id = "pd-${product.productGroup}">
					<div class="items" id = "pd-${product.productGroup}">
						<div class = "event">
							<div class="item">
							    <img src="/image/product/${product.files[0].fileName}">
							</div>
							<div>
							    <p>${product.productBrand}</p>
							    <p>${product.productName}</p>
							    <div>
							        ${price},000 원
							    </div>
							</div>
						</div>
					</div>
				</div>
				`;
					
		}		
		item = product.productName;		
		
	})
	addEvent(list);
}
function subStringProductGroup(items){
	//console.log(items);
	
	const itemSelection = items.querySelector(".items");
	//console.log("itemSelection : " + itemSelection);
	
	const productGroup = itemSelection.getAttribute("id");
	//console.log("productGroup : " + productGroup);
	
	const tokenIndex = productGroup.lastIndexOf("-");
	//console.log("tokenIndex : " + tokenIndex);
	
	//console.log("productCode.substring(tokenIndex + 1) : " + productCode.substring(tokenIndex + 1));
	//console.log("  ");
	return productGroup.substring(tokenIndex + 1);
}

function addEvent(list){
	const itemSelection = document.querySelectorAll(".item-selection");
	console.log(list);
	for(let items of itemSelection) {
		//console.log(items);
		let defaultDiv = itemSelection;
		//console.log(itemSelection);
 		const productGroup = subStringProductGroup(items);
 		
		mouseEvent(items, productGroup, list, defaultDiv);
	}
		//console.log(productCode);
}


function mouseEvent(items, productGroup, list, defaultDiv){
	const itemSelection = items.querySelector(".item");
	//console.log(defaultDiv);
	
	itemSelection.onclick = () => {
		location.href = "/product/" + productGroup;
	}
	
	itemSelection.onmouseover = () => {
		console.log("over + " + productGroup);
	}
	itemSelection.onmouseout = () => {
		console.log("out + " + productGroup);
	}
}

function reLoad(){
	
}
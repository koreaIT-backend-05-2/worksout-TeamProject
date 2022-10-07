const filterButton = document.querySelector(".filter-button");
const filter = document.querySelector(".filters");
const maleButton = document.querySelector(".gender-male");
const femaleButton = document.querySelector(".gender-female");
const etcButton = document.querySelector(".gender-etc")

let genderSelect = "m";
const type = 1;

load(genderSelect);
 

maleButton.onclick=()=>{
	genderSelect = "m";
	load(genderSelect);
}
femaleButton.onclick=()=>{
	genderSelect = "f";
	load(genderSelect);
}
etcButton.onclick=()=>{
	genderSelect = "e";
	load(genderSelect);
}



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

			let price = product.productPrice / 1000;
			
			let imgFiles = new Array();
			
			let i = 0;
			
			for(i = 0 ; i < product.files.length ; i++){
				imgFiles = product.files[i].fileName;
				console.log("productCode = "+ product.productCode);
				console.log("imgFiles = " + imgFiles);
			}
			
			
			loadedItems.innerHTML += `
				<div class = "item-selection" id = "pd-${product.productCode}">
					<div class="items" id = "pd-${product.productCode}">
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
function subStringProductCode(items){
	//console.log(items);
	
	const itemSelection = items.querySelector(".items");
	//console.log("itemSelection : " + itemSelection);
	
	const productCode = itemSelection.getAttribute("id");
	//console.log("productCode : " + productCode);
	
	const tokenIndex = productCode.lastIndexOf("-");
	//console.log("tokenIndex : " + tokenIndex);
	
	//console.log("productCode.substring(tokenIndex + 1) : " + productCode.substring(tokenIndex + 1));
	//console.log("  ");
	return productCode.substring(tokenIndex + 1);
}

function addEvent(list){
	const itemSelection = document.querySelectorAll(".item-selection");
	
	for(let items of itemSelection) {
		//console.log(items);
		let defaultDiv = itemSelection;
		//console.log(itemSelection);
 		const productCode = subStringProductCode(items);
 		
		mouseEvent(items, productCode, list, defaultDiv);
	}
		//console.log(productCode);
}


function mouseEvent(items, productCode, list, defaultDiv){
	const itemSelection = items.querySelector(".item");
	console.log(defaultDiv);
	
	itemSelection.onclick = () => {
		location.href = "/product/" + productCode;
	}
	
	itemSelection.onmouseover = () => {
		console.log("over + " + productCode);
	}
	itemSelection.onmouseout = () => {
		console.log("out + " + productCode);
	}
}

function reLoad(){
	
}
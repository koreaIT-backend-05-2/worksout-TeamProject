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
				console.log(imgFiles);
			}
			
			
			loadedItems.innerHTML += `
			<div class = "item-selection"> 
				<div class="items">
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
				`;
					
		}		
		item = product.productName;		
		
	})
	addEvent();
}
function subStringProductCode(items){
	const productContents = items.querySelector(".pd");
	console.log("tdProductContents : " + productContents);
	
	const productCode = productContents.getAttribute("id");
	console.log("productCode : " + productCode);
	
	const tokenIndex = productCode.lastIndexOf("-");
	console.log("tokenIndex : " + tokenIndex);
	
	console.log("productCode.substring(tokenIndex + 1) : " + productCode.substring(tokenIndex + 1));
	console.log("  ");
	return productCode.substring(tokenIndex + 1);
}

function addEvent(){
	const itemSelection = document.querySelectorAll(".item-selection");

	for(let items of itemSelection) {
 		const productCode = subStringProductCode(items);
 		
		addSelectEvent(items, productCode);
	}
		//console.log(productCode);
}


function addSelectEvent(items, productCode){
	const itemSelection = items.querySelector(".item-selection");
	itemSelection.onmouseover = () => {
		console.log("over");
//		console.log(removeChk());
		//if(removeChk()){
		//	deleteProduct(pdContent, productCode);
		//}
	}
	itemSelection.onmouseout = () => {
		console.log("out");
	}
}
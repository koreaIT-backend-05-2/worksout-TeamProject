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
	let count = 0;
	
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
			<div class = "selection"> 
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
			
			const selection = document.querySelector(".selection");
			selection.onmouseover = () => {
				console.log("over");
			}
			
		
			loadedItems.onmouseover = () => {
				
				//const imageChange = document.querySelector(".item");
				//imageChange.innerHTML = "";
				//imageChange.innerHTML = `
				//	<div class="item">
				//		    <img src="/image/product/${product.files[1].fileName}">
				//	</div>
				//`;
				
				//console.log("in");
			}
			loadedItems.onmouseout = () => {
				//const imageChange = document.querySelector(".item");
				//imageChange.innerHTML = "";
				//imageChange.innerHTML = `
				//	<div class="item">
				//		    <img src="/image/product/${product.files[0].fileName}">
				//	</div>
				//`;
				//console.log("out");
			}
		
		
		}		
		item = product.productName;
		count++;	
		
	})
}
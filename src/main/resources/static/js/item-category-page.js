const filterButton = document.querySelector(".filter-button");
const filter = document.querySelector(".filters");

// male, female, etc
/** 
	male = 1
	female =2
	etc = 3
 */

let items = [];
let item = null;
 
const type = 1;

load();


filterButton.onclick = () => {
   filter.classList.toggle("hidden");
}

function load(){
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
			loadItems(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function loadItems(list) {
	let count = 0;
	list.forEach(product => {
	
		
		if(item == product) console.log(count++);
		
		item = product.productName;
		
		console.log("item : " + item);
		//console.log("product.productName : " + product.productName);
	})
}
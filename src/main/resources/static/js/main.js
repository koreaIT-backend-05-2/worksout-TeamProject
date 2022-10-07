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
	console.log(list);

	const mainContainer = document.querySelector(".main-container");
	let arr = new Array();
	
	mainContainer.innerHTML = "";
	
	for(let i = 0; i < list.length ; i++){
		if(list[i].productGender == "MainImage") arr.push(i);
	}
	//console.log(arr);
	
	for(let i = 0 ; i < 2 ; i++){
		console.log(list[arr[i]]);
		mainContainer.innerHTML += `
			<div class="a">
                <div>
                    <img src="/image/main/${list[arr[i]].files[0].fileName}" class="first-img">
                </div>
                <h1>
                    <div>
                        <p>${list[arr[i]].productName}</p>
                    </div>
                </h1>
            </div>
		`;
	}

	let count = 0;
	let item = null;
	
	list.forEach(product => {
		if(item != product.productName && count < 14){
			mainContainer.innerHTML += `
				<div class="a">
	                <div>
	                    <img src="/image/product/${product.files[0].fileName}" class="first-img">
	                </div>
	                <h1>
	                    <div>
	                        <p>${product.productName}</p>
	                    </div>
	                </h1>
	            </div>
			`;
		}else{
			return false;
		}
		item = product.productName;
		count++;
	});
	
}
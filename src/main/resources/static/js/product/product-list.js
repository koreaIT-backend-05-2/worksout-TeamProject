const updateButton = document.querySelector(".search-button");

let preProductCode = 0;

load();

updateButton.onclick = () => {
	const productCodeForUpdate = document.getElementById('inputValue').value;
	
	//alert("상품코드 값은 " + productCodeForUpdate);
	
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/admin/${productCodeForUpdate}`,
		datatype : "json",
		success:(response) => {
			console.log(response.data);
			getProductByProductCode(response.data);
		}, error: (error) => {
			console.log(error);
		}
	})
}



function load() {
	const searchFlag = document.querySelector(".search-select").value;
	const searchValue = document.querySelector(".search-input").value;
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/admin/list/" + 1,
		data: {
			"searchFlag": searchFlag,
			"searchValue": searchValue
		},
		dataType: "json",
		success:(response) => {
				getList(response.data);
				console.log(response.data);
//			if(response.data[0] != null){
//				getList(response.data);
//				console.log(response.data);
//				getPageNumbers(response.data[0].totalNoticeCount);				
//				console.log("totalNoticeCount: " + response.data[0].totalNoticeCount);			
//			}else {
				//getList(new Array());
				//getPageNumbers(0);
//			}
		},
		error: (error) => {
			console.log(error);
		}
		
	})
}

function updateProductContent(productCode, product){
	let successFlag = false;
	$.ajax ({
		async: false,
		type: "put",
		url: `/api/v1/admin/edit/${productCode}`,
		contentType: "application/json",
		data : JSON.stringify({
			"productCode" : productCode,
			"product" : product
		}),
		dataType : "JSON",
		success: (response) => {
			successFlag = response.data;	
		},
		error: (error) => {
			console.log(error);
		}
	})
	return successFlag;
}

function getList(list) {
	const tbody = document.querySelector("tbody");
	
	tbody.innerHTML = "";
	
	list.forEach(product => {
		let files = "";
		
		for(let i = 0; i < product.files.length; i++) {
			files += `<a href="">${product.files[i].fileName}</a>`
			if(i < product.files.length - 1){
				files += " / ";
			}
		}
		
		tbody.innerHTML += `
			<tr id = "product-contents-${product.productCode}" class = "product-contents">
	            <td id = "pd-${product.productCode}" class = "pd">${product.productCode}</td>
	            <td>${product.productBrand}</td>
	            <td>${product.productKind}</td>
	            <td>${product.productName}</td>
	            <td>${product.productPrice}</td>
	            <td>${product.productAmount}</td>
	            <td>${product.productSize}</td>
	            <td>${product.productGender}</td>
	            <td>${files}</td>
	            <td>
	                <button type="button" class="deleteButton">삭제</button>
	            </td>
	        </tr>
		`;
		
	});
	addEvent();
}




function getProductByProductCode (product) {
//	console.log(product);
	
	const adminContentUpdate = document.querySelector(".admin-content-update");
	let files = "";
	
	for(let i = 0; i < product.files.length; i++) {
			files += `<a href="">${product.files[i].fileName}</a>`
			if(i < product.files.length - 1){
				files += " / ";
			}
		}
	
	adminContentUpdate.innerHTML = `
		<div class="insert-items">
			<input type="text" placeholder="product_brand" name="productBrand" value="${product.productBrand}">
			<input type="text" placeholder="product_kind" name="productKind" value="${product.productKind}">
			<input type="text" placeholder="product_name" name="productName" value="${product.productName}">
			<input type="text" placeholder="product_info" name="productInfo" value="${product.productInfo}">
			<input type="text" placeholder="product_price" name="productPrice" value="${product.productPrice}">
			<input type="text" placeholder="product_amount" name="productAmount" value="${product.productAmount}">
			<input type="text" placeholder="product_size" name="productSize" value="${product.productSize}">
			<input type="text" placeholder="product_gender" name="productGender" value="${product.productGender}">
			<input type="file" class="product-file" name="file" multiple="multiple" accept=".jpg, .jpeg, .png, .gif"> 
			<div id = "preview-images">${files}</div>
		</div>
		<button type="button" class="cancel-update-button">수정하지않기</button>
		<button type="button" class="update-button">수정하기</button>
	`;
	const cancelUpdateButton = document.querySelector(".cancel-update-button");
	const updateButton = document.querySelector(".update-button");
	
	cancelUpdateButton.onclick=()=>{
		location.href = "/admin/itemlist";
	}
	
	updateButton.onclick=()=>{
		updateProductContent(product.productCode, product);
	}
}

//			<div id = "preview-images">${readImage(files)}</div>

function readImage(files){
	if(files && files[0]){
		
		const reader = new FileReader();
		
		reader.onload = (e) => {
			const previewImage = document.getElementById("preview-images");
			previewImage.src = e.target.result;
		}
		
		reader.readAsDataURL(files[0]);
	}
}

function subStringProductCode(productContent){
	const tdProductContents = productContent.querySelector(".pd");
	console.log("tdProductContents : " + tdProductContents);
	const productCode = tdProductContents.getAttribute("id");
	console.log("productCode : " + productCode);
	const tokenIndex = productCode.lastIndexOf("-");
	console.log("tokenIndex : " + tokenIndex);
	
	console.log("productCode.substring(tokenIndex + 1) : " + productCode.substring(tokenIndex + 1));
	console.log("  ");
	return productCode.substring(tokenIndex + 1);
}

function addEvent(){
	const productContents = document.querySelectorAll(".product-contents");

	for(let pdContent of productContents) {
 		const productCode = subStringProductCode(pdContent);
 		
		addDeleteEvent(pdContent, productCode);
	}
		//console.log(productCode);
}


function addDeleteEvent(pdContent, productCode){
	const deleteButton = pdContent.querySelector(".deleteButton");
	deleteButton.onclick = () => {
		console.log(productCode);
//		console.log(removeChk());
		if(removeChk()){
			deleteProduct(pdContent, productCode);
		}
	}
}


function deleteProduct(productContent, productCode) {
	$.ajax({
		type: "delete",
		url: `/api/v1/admin/delete/${productCode}`,
		async: false,
		dataType: "json",
		success: (response) => {
			//if (response.data) {
				//productContentList.removeChild(productContent);
			//}
			location.href = "/admin/itemlist";
		},
		error: errorMessage
	})
}

function removeChk(){
	return confirm("정말 삭제 하시겠습니까?") ? true : false;
}
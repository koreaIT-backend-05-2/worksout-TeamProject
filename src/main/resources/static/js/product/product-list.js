
let nowPage = 1;

load(nowPage);

function load(nowPage) {
	const searchFlag = document.querySelector(".search-select").value;
	const searchValue = document.querySelector(".search-input").value;
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/admin/list/" + nowPage,
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

function getList(list) {
	const tbody = document.querySelector("tbody");
	let listCount = 0;
	let map = new Map();
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
	            <td>${files}</td>
	            <td>
	                <button type="button" class="updateButton">수정</button>
	                <button type="button" class="deleteButton">삭제</button>
	            </td>
	        </tr>
		`;
		
	});
	addEvent();
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
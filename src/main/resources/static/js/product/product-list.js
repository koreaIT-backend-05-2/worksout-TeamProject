
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
			<tr>
	            <td>${product.productCode}</td>
	            <td>${product.productBrand}</td>
	            <td>${product.productKind}</td>
	            <td>${product.productName}</td>
	            <td>${product.productPrice}</td>
	            <td>${product.productAmount}</td>
	            <td>${product.productSize}</td>
	            <td>${files}</td>
	            <td>
	                <button type="button">수정</button>
	                <button type="button">삭제</button>
	            </td>
	        </tr>
		`;
	});
}

function deleteProduct(productContent, productCode) {
	$.ajax({
		type: "delete",
		url: `/api/v1/admin/delete/${productCode}`,
		async: false,
		dataType: "json",
		success: (response) => {
			if (response.data) {
				productContentList.removeChild(productContent);
			}
		},
		error: errorMessage
	})
}
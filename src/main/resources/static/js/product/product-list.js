

function load() {
	$.ajax({
		async:false,
		type:"get",
		url:"api/v1/search",
		
	})
}

function getList() {
	const tbody = document.querySelector("tbody");
	tbody.innerHTML = "";
	
	list.forEach(product => {
		tbody.innerHTML += `
			<tr>
	            <td>${product.productCode}</td>
	            <td>${product.productBrand}</td>
	            <td>${product.productKind}</td>
	            <td>${product.productName}</td>
	            <td>${product.productPrice}</td>
	            <td>${product.productAmount}</td>
	            <td>${product.productSize}</td>
	            <td>${product.fileName}</td>
	            <td>
	                <button type="button">수정</button>
	                <button type="button">삭제</button>
	            </td>
	        </tr>
		`;
	});
}
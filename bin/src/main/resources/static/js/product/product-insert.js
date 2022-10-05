const addButton = document.querySelector(".addbutton");

addButton.onclick = () => {
	alert("click");
	
	let formData = new FormData(document.querySelector("form"));
    
    //formData.append("productCode", get)
    
    formData.forEach((v, k) => {
		console.log("key: " + k);
		console.log("value: " + v);
	});
	
	$.ajax({
		async: false,
		type:"post",
		url: "/api/v1/admin/insert",
		enctype: "multipart/form-data",
		contentType: false,
		processData: false,
		data: formData,
		dataType: "json",
		success: (response) => {
			alert(response.data);
			/** location.href = "/admin/add-items";*/
		},
		error:errorMessage
	});	
}

/*
function createProductContent() {
	$.ajax({
		async: false,
		type:"post",
		url: "api/v1/admin/insert",
		enctype: "multipart/form-data",
		currentType: false,
		processData: false,
		data: formData,
		dataType: "json",
		success: (response) => {
			alert(response.data);
			location.href = "/admin/add-items";
		},
		error:errorMessage
	})
}
 */

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}
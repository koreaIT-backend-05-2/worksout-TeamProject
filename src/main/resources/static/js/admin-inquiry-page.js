const kindSearchBtn = document.querySelector(".kind-search-button");
let page = 1;

load(page);

kindSearchBtn.onclick = () => {
	
	load(page);
}


function load (page) {
	let searchFlag = document.querySelector(".search-select").value;
	
	
	console.log("searchFlag: " + searchFlag);
	
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/inquiry/list/" + page,
		data: {
			"searchFlag": searchFlag
		},
		dataType: "json",
		success: (response) => {
			getList(response.data)
		},
		error: errorMessage
		
	});
}

function getList(list) {
	const tbody = document.querySelector("tbody");
	
	tbody.innerHTML = "";
	
	list.forEach(inquiry => {
		tbody.innerHTML += `
			<tr class="inquiry-lists">
                <td class="inquiry-header-box">${inquiry.inquiryCode}</td>
                <td class="inquiry-header-box">${inquiry.inquiryKind}</td>
                <td class="inquiry-header-box">${inquiry.inquiryName}</td>
                <td class="inquiry-header-box">${inquiry.inquiryEmail}</td>
                <td class="inquiry-header-box2">${inquiry.inquiryTitle}</td>
                <td class="inquiry-header-box">${inquiry.createDate}</td>
            </tr>
		`
	});
	
	const detailList = document.querySelectorAll(".inquiry-lists");
	console.log(detailList);

	detailList.forEach((inquiry) => {
		inquiry.onclick = () => {
			const inquiryCode = inquiry.querySelectorAll("td")[0].textContent
			location.href = "/admin/inquiry/detail/" + inquiryCode
		}
	})
	
}



//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}
const listBtn = document.querySelector(".list-button");
const preBtn = document.querySelector(".pre-button");
const nextBtn = document.querySelector(".next-button");

let inquiryCode = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);

load("/api/v1/inquiry/");

function load(uri) {
	
	$.ajax({
		async: false,
		type: "get",
		url: uri + inquiryCode,
		dataType: "json",
		success: (response) => {
			getInquiry(response.data);
		},
		error : errorMessage
	});
}

function getInquiry(inquiry) {
	const inquiryTitle = document.querySelector(".inquiry-title");
	const inquiryDetailDescription = document.querySelectorAll(".inquiry-detail-description h3");
	const inquiryContent = document.querySelector(".inquiry-content");
	const inquiryFile = document.querySelector(".inquiry-files");
	
	inquiryCode = inquiry.inquiryCode;
	
	inquiryTitle.innerHTML = inquiry.inquiryTitle;
	inquiryDetailDescription[0].innerHTML = "종류: " + inquiry.inquiryKind;
	inquiryDetailDescription[1].innerHTML = "작성자: " + inquiry.inquiryName;
	inquiryDetailDescription[2].innerHTML = "이메일: " + inquiry.inquiryEmail;
	inquiryDetailDescription[3].innerHTML = "작성일: " + inquiry.createDate;
	inquiryContent.innerHTML = inquiry.inquiryContent;
	
	inquiryFile.innerHTML = `<h3>첨부파일: </h3>`;
	
	let inquiryFileArray = new Array();
	inquiry.downloadFiles.forEach(file => {
		if(file.fileCode != undefined) {
			inquiryFileArray.push(`
			<a href="/api/v1/inquiry/${file.fileCode}">${file.fileName}</a>
		`) 
		} else {
			inquiryFileArray.push(`<p>파일 없음</p>`)
		}
		
	});
	
	inquiryFile.innerHTML += inquiryFileArray.join(" / ");
	
}

listBtn.onclick = () => {
	location.href = "/admin/inquiry";
}

preBtn.onclick = () => {
	load("/api/v1/inquiry/pre/");
}

nextBtn.onclick = () => {
	load("/api/v1/inquiry/next/");
}


//에러 메시지
function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}
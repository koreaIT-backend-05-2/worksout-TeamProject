//파일 버튼
const fileInput = document.querySelector(".file-input");
const fileNameList = document.querySelector(".file-name-list");
let fileName = document.querySelector("#fileName");


fileInput.onchange = () => {
		fileList();
}

function fileList() {
		const files = fileInput.files;
		const fileListLength = files.length;
		const cancelBtn = document.querySelector(".cancle-button");
		
		
		for(let i = 0; i < fileListLength; i++) {
			fileName.innerHTML += `<li>${files.item(i).name}</li> `;
			//fileName.innerText = `${fileName.innerText}\n${files.item(i).name}`;
			fileName.style.fontSize = "15px";
		}
		/*
		let cancleNameList = new Array();
		let cancleIndexList = new Array();
		
		function cancelFile(fileName, fileIndex) {
			cancleNameList.push(fileName);
			cancleIndexList .push(fileIndex);
			
			document.querySelector(`.file-${fileIndex}`).classList.add("visible");
			
		}
		
		function cancelAddFile(fileName) {
			
		    addFileList = addFileList.filter(file => file.name != fileName);
		
		}
		*/
		
		cancelBtn.onclick = () => {
			if(fileName.innerText != undefined) {
				fileName.innerText = "";
			}
		}
}

//ajax 요청
const submitBtn = document.querySelector(".submit-button");

submitBtn.onclick = () => {
	load();
}

function changeBtn() {
	const inputs = document.querySelectorAll("input");
	const textArea = document.querySelector("textarea");
	const selects = document.querySelector("select");
	
	console.log(inputs)	;
	console.log(textArea);
	console.log(selects);
	
	textArea.onkeyup = () => {
		submitBtn.style.backgroundColor = "black"
		
		if(textArea.value == "") {
			submitBtn.style.backgroundColor = ""
		}
	}
	
	inputs[0].onkeyup = () => {
		if(inputs[0].value == "") {
			submitBtn.style.backgroundColor = ""
		}
	}
	
	inputs[1].onkeyup = () => {
		if(inputs[1].value == "") {
			submitBtn.style.backgroundColor = ""
		}
	}
	
	inputs[2].onkeyup = () => {
		if(inputs[2].value == "") {
			submitBtn.style.backgroundColor = ""
		}
	}
	
	selects.onchange = () => {
		if(selects.value == "") {
			submitBtn.style.backgroundColor = ""
		}
	}
};

changeBtn();

function load() {
	
	let formData = new FormData(document.querySelector(".middle-category-box"));
	/* formData.append("cancleFile", fileInput.files) */
	
	formData.forEach((v, k) => {
		console.log("key: " + k);
		console.log("value: " + v);
	})
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/inquiry",
		enctype: "multipart/form-data",
		contentType: false,
		processData: false,
		data: formData,
		dataType: "json",
		success: (response) => {
			alert("문의가 접수되었습니다.")
			location.reload(true);
		},
		error: errorMessage
	});
}

//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}
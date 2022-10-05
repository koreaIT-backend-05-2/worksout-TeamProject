const genderEtcCategory = document.querySelector(".gender-etc-category");
const hiddenCategoryMenu = document.querySelector(".hidden-category-menu");

//genderEtcCategory.onclick = () => {
//    hiddenCategoryMenu.classList.toggle("hidden");
    
//}

/*

	로그인시 로그아웃 나타남

*/

const authItems = document.querySelector(".auth-items");


function getPrincipal() {
	let user = null;
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/auth/principal",
		dataType: "json",
		success: (response) => {
			user = response.data;
		}
		
	});
	
	return user;
}

function loadHeader(user) {
	
	
	if(user == null) {
		authItems.innerHTML = `
	         <p class="login-and-logout user-login">로그인</p>
		`
		const login = document.querySelector(".user-login");
		
		login.onclick = () => {
			location.href = "/signin";
		}
		
	}else {
			authItems.innerHTML = `
			<p class="hidden-my-page my-page">마이페이지</p>
	         <p class="login-and-logout logout">로그아웃</p>
		`
	
		const logout = document.querySelector(".logout");
		const myPage = document.querySelector(".my-page");
	
		logout.onclick = () => {
			location.replace("/logout");
		}
		
		myPage.onclick = () => {
						
		}
		
	}
}


let user = getPrincipal();

loadHeader(user);



//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}

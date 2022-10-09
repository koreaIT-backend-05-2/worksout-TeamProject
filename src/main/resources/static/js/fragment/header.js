const mainLogoImage = document.querySelector(".main-logo-image");
const entryPage = document.querySelector(".entry-page");
const cartPage = document.querySelector(".cart-page");

entryPage.onclick = () => {
	location.href = "/entry";
}
cartPage.onclick = () => {
	location.href = "/cart";	
}

mainLogoImage.onclick = () => {
	location.href = "/main";
}

const headerSection = document.querySelector(".header-section");
const genderEtcCategory = document.querySelectorAll(".gender-etc-category");
const hiddenCategoryMenu = document.querySelector(".hidden-category-menu");

for(let i = 0 ; i < genderEtcCategory.length ; i++) {
	
	genderEtcCategory[i].onmouseover = () => {
	    hiddenCategoryMenu.classList.toggle("hidden");
//	    hiddenCategoryMenu.classList.add("active");
//	    hiddenCategoryMenu.classList.remove("hidden");
//		hiddenCategoryMenu.onmouseout =() => {
//			hiddenCategoryMenu.classList.toggle("hidden");
//		}
		console.log("젠더 i번째 버튼 = ");
		console.log(i);
	}		
//	hiddenCategoryMenu.onmouseout =() => {
//		hiddenCategoryMenu.classList.remove("active");
//		hiddenCategoryMenu.classList.add("hidden");		
//	}
}





const maleButton = document.querySelector(".gender-male");
const femaleButton = document.querySelector(".gender-female");
const etcButton = document.querySelector(".gender-etc")

maleButton.onclick=()=>{
	location.href = "/category/m";
}
femaleButton.onclick=()=>{
	location.href = "/category/f";
}
etcButton.onclick=()=>{
	location.href = "/category/e";
}




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
			location.href = "/mypage/modify";			
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

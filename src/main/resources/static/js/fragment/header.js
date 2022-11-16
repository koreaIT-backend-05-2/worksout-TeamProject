const mainLogoImage = document.querySelector(".main-logo-image");
const entryPage =document.querySelector(".entry-page");
const cartPage =document.querySelector(".cart-page");


entryPage.onclick = () => {
	location.href = "/entry"
}

cartPage.onclick = () => {
	location.href = "/cart"
}

mainLogoImage.onclick = () => {
	location.href = "/main";
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

//헤더 이벤트 

const headerBtns = document.querySelectorAll(".gender-etc-category button");
const hiddenCategoryMenu = document.querySelector(".hidden-category-menu");

console.log(">>>" + headerBtns)

for(let i = 0; i < headerBtns.length; i++) {
	headerBtns[i].onmouseover = () => {
		hiddenCategoryMenu.classList.add("active");
		
	}
}


for(let i = 0; i < headerBtns.length; i++) {
	hiddenCategoryMenu.onmouseleave = () => {
		hiddenCategoryMenu.classList.remove("active");
		
	}
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
			console.log(user);
		}
		
	});
	
	return user;
}


function loadHeader(user) {
	
	let roles = "ROLE_ADMIN";
	if(user == null) {
		authItems.innerHTML = `
	         <p class="login-and-logout user-login">로그인</p>
		`
		
		const login = document.querySelector(".user-login");
		
		login.onclick = () => {
			location.href = "/signin";
		}
		
	}else if(user.userRoles.includes(roles)) {
		
		const headerSectionStart = document.querySelector(".header-section-start");
		let logout = document.querySelector(".login-and-logout");
		
		console.log(logout)
			
			headerSectionStart.innerHTML = `
			<a href="/entry" class="entry-page">응모</a>
            <a href="https://www.worksout.co.kr/store/worksout" class="store-info">매장 정보</a>
			<a href = "/admin/itemlist" class= "admin-page">관리자 페이지로 이동하기</a>
			`
			
			authItems.innerHTML = `
			<p class="hidden-my-page my-page" onclick = "location.href ='/mypage/modify'">마이페이지</p>
	         <p class="login-and-logout logout" onclick = "location.replace('/logout')">로그아웃</p>
		`
		
			console.log(user)
			cartListNumEve(user);
		
	}else{
		
			authItems.innerHTML = `
			<p class="hidden-my-page my-page" onclick = "location.href ='/mypage/modify'">마이페이지</p>
	         <p class="login-and-logout logout" onclick = "location.replace('/logout')">로그아웃</p>
		`
		
		
			console.log(user)
			cartListNumEve(user);
		
	}
}


let user = getPrincipal();

loadHeader(user);



//장바구니 옆에 개수 넣어주는 이벤트
function cartListNumEve(user) {
	let cartList = localStorage.getItem("productListToken")

	console.log(cartList)
	
	if(user != null) {
		cartPage.innerHTML = `장바구니(${cartList})`;
	}
}




//에러메시지

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}

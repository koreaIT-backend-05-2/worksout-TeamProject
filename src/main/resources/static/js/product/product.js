const userInfoButton = document.querySelector(".user-info");
const pdListButton = document.querySelector(".pd-list");
const pdInsertButton = document.querySelector(".pd-insert");
const inquiryButton = document.querySelector(".inquiry");

userInfoButton.onclick = () => {
	location.href = "/admin/user"
}
pdListButton.onclick = () => {
	location.href = "/admin/itemlist";
}
pdInsertButton.onclick = () => {
	location.href = "/admin/add-items";
}
inquiryButton.onclick = () => {
	location.href = "/admin/inquiry";
}
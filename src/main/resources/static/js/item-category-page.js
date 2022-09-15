const filterButton = document.querySelector(".filter-button");
const filter = document.querySelector(".filters");

const genderEtcCategory = document.querySelector(".gender-etc-category");
const hiddenCategoryMenu = document.querySelector(".hidden-category-menu");

let flag = 0;

if(flag == 0){
    filter.classList.toggle("hidden");
    flag = 1;
}

filterButton.onclick = () => {
    filter.classList.toggle("hidden");
}

genderEtcCategory.onclick = () => {
    hiddenCategoryMenu.classList.toggle("hidden");
}
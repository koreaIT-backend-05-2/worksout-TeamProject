const genderEtcCategory = document.querySelector(".gender-etc-category");
const hiddenCategoryMenu = document.querySelector(".hidden-category-menu");

const filterButton = document.querySelector(".filter-button");
const filter = document.querySelector(".filters");


filterButton.onclick = () => {
    filter.classList.toggle("hidden");
}

genderEtcCategory.onclick = () => {
    hiddenCategoryMenu.classList.toggle("hidden");
    console.log("hidden");
}
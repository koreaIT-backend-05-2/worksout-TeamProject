const genderEtcCategory = document.querySelector(".gender-etc-category");
const hiddenCategoryMenu = document.querySelector(".hidden-category-menu");

genderEtcCategory.onclick = () => {
    hiddenCategoryMenu.classList.toggle("hidden");
}


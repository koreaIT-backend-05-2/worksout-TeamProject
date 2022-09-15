const filterButton = document.querySelector(".filter-button");
const filter = document.querySelector(".filters");

let flag = 0;

if(flag == 0){
    filter.classList.toggle("hidden");
    flag = 1;
}

filterButton.onclick = () => {
   filter.classList.toggle("hidden");
}
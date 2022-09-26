const minus = document.querySelector(".minus");
const plus = document.querySelector(".plus");
const amountText = document.querySelector(".amount");
const productPrice = document.querySelector(".td-price");

console.log(plus);

let amount = 1;

let price = 219900;

plus.onclick = () => {
    if(amountText.innerHTML != 15) {
        amountText.innerHTML = ++amount;
        productPrice.innerHTML = ++price;
    }
}

minus.onclick = () => {
    if(amountText.innerHTML == 1) {
        amountText.innerHTML = amount;
    }else {
        amountText.innerHTML = --amount;
    }
}
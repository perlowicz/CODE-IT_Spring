function validateForm() {
    const nameInput = document.getElementById('name');
    const descriptionInput = document.getElementById('description');
    const priceInput = document.getElementById('price');

    const errorName = document.getElementById('errorName');
    const errorDescription = document.getElementById('errorDescription');
    const errorPrice = document.getElementById('errorPrice');

    resetErrors([nameInput,descriptionInput,priceInput],[errorName,errorDescription,errorPrice], errorsSummary);

    let valid = true;

    // Walidacja dla pola name
    if(!checkRequired(nameInput.value)){
        valid = false;
        nameInput.classList.add("error-input");
        errorName.innerText = "Pole jest wymagane";
    } else if(!checkTextLengthRange(nameInput.value, 2, 50)){
        valid = false;
        nameInput.classList.add("error-input");
        errorName.innerText = "Pole powinno zawierac od 2 do 50 znakow";
    }

    // Walidacja dla pola description
    if(!checkRequired(descriptionInput.value)){
        valid = false;
        descriptionInput.classList.add("error-input");
        errorDescription.innerText = "Pole jest wymagane";
    } else if(!checkTextLengthRange(descriptionInput.value, 50, 200)){
        valid = false;
        descriptionInput.classList.add("error-input");
        errorDescription.innerText = "Pole powinno zawierac od 50 do 200 znakow";
    }

    // Walidacja dla pola price
    if(!checkRequired(priceInput.value)){
        valid = false;
        priceInput.classList.add("error-input");
        errorPrice.innerText = "Pole jest wymagane";
    } else if(!checkMinPrice(priceInput.value)){
        valid = false;
        priceInput.classList.add("error-input");
        errorPrice.innerText = "Podana cena jest ponizej minimalnej - 200zl";
    } else if(!checkPrice(priceInput.value)){
        valid = false;
        priceInput.classList.add("error-input");
        errorPrice.innerText = "Podana cena nie jest wielokrotnoscia 50zl";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera bledy";
    }

    return valid;
}
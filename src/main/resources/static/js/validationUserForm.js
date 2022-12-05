function validateForm() {
    const userNameInput = document.getElementById('userName');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const registrationInput = document.getElementById('registrationDate');

    const errorUsername = document.getElementById('errorUsername');
    const errorEmail = document.getElementById('errorEmail');
    const errorPassword = document.getElementById('errorPassword');
    const errorRegistration = document.getElementById('errorRegistration');

    resetErrors([userNameInput,emailInput,passwordInput,registrationInput],[errorUsername,errorEmail,errorPassword,errorRegistration], errorsSummary);

    let valid = true;

    // Walidacja dla pola username
    if (!checkRequired(userNameInput.value)) {
        valid = false;
        userNameInput.classList.add("error-input");
        errorUsername.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(userNameInput.value, 7, 50)) {
        valid = false;
        userNameInput.classList.add("error-input");
        errorUsername.innerText = "Pole powinno zawierac od 7 do 50 znakow";
    }

    // Walidacja dla pola email
    if (!checkRequired(emailInput.value)) {
        valid = false;
        emailInput.classList.add("error-input");
        errorEmail.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(emailInput.value, 5, 100)) {
        valid = false;
        emailInput.classList.add("error-input");
        errorEmail.innerText = "Pole powinno zawierac od 5 do 100 znakow";
    } 
    else if (!checkEmail(emailInput.value)) {
        valid = false;
        emailInput.classList.add("error-input");
        errorEmail.innerText = "Pole powinno zawierac prawidlowy adres email";
    }

    // Walidacja dla pola password
    if (!checkRequired(passwordInput.value)) {
        valid = false;
        passwordInput.classList.add("error-input");
        errorPassword.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(passwordInput.value, 8, 20)) {
        valid = false;
        passwordInput.classList.add("error-input");
        errorPassword.innerText = "Pole powinno zawierac od 8 do 20 znakow";
    }

    // Walidacja dla pola registration
    if (!checkRequired(registrationInput.value)){
        valid = false;
        registrationInput.classList.add("error-input");
        errorRegistration.innerText = "Pole jest wymagane";
    } else if (!checkDate(registrationInput.value)) {
        valid = false;
        registrationInput.classList.add("error-input");
        errorRegistration.innerText = "Data nie moze byc z przyszlosci";
    } else if (!checkDateFormat(registrationInput.value)){
        valid = false;
        registrationInput.classList.add("error-input");
        errorRegistration.innerText = "Data w zlym formacie";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera bledy";
    }

    return valid;
}
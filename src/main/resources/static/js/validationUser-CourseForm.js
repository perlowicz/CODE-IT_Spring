function validateForm() {
    const userInput = document.getElementById('user');
    const courseInput = document.getElementById('course');
    const signupInput = document.getElementById('signup');
    const expiringInput = document.getElementById('expiring');

    const errorUser = document.getElementById('errorUser');
    const errorCourse = document.getElementById('errorCourse');
    const errorSignup = document.getElementById('errorSignup');
    const errorExpiring = document.getElementById('errorExpiring');

    resetErrors([userInput,courseInput,signupInput,expiringInput],[errorUser,errorCourse,errorSignup,errorExpiring], errorsSummary);

    let valid = true;

    // Walidacja dla pola user
    if(!checkRequired(userInput.value)){
        valid = false;
        userInput.classList.add("error-input");
        errorUser.innerText = "Pole jest wymagane";
    }

    // Walidacja dla pola course
    if(!checkRequired(courseInput.value)){
        valid = false;
        courseInput.classList.add("error-input");
        errorCourse.innerText = "Pole jest wymagane";
    }

    // Walidacja dla pola signup
    if(!checkRequired(signupInput.value)){
        valid = false;
        signupInput.classList.add("error-input");
        errorSignup.innerText = "Pole jest wymagane";
    } else if(!checkDate(signupInput.value)){
        valid = false;
        signupInput.classList.add("error-input");
        errorSignup.innerText = "Data rejestracji nie moze byc z przyszlosci";
    } else if(!checkDateFormat(signupInput.value)){
        valid = false;
        signupInput.classList.add("error-input");
        errorSignup.innerText = "Data w zlym formacie";
    }

    // Walidacja dla pola expiring
    if(!checkRequired(expiringInput.value)){
        valid = false;
        expiringInput.classList.add("error-input");
        errorExpiring.innerText = "Pole jest wymagane";
    } else if(!checkExpiringDate(expiringInput.value, signupInput.value)){
        valid = false;
        expiringInput.classList.add("error-input");
        errorExpiring.innerText = "Data wygasniecia licencji nie moze byc wczesniej niz data rejestracji zakupu";
    } else if(!checkDateFormat(expiringInput.value)){
        valid = false;
        expiringInput.classList.add("error-input");
        errorExpiring.innerText = "Data w zlym formacie";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera bledy";
    }

    return valid;
}
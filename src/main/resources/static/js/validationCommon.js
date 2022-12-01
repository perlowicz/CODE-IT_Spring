function resetErrors(inputs, errorTexts, errorInfo) {
    for(let i=0; i<inputs.length; i++){
        inputs[i].classList.remove("error-input");
    }
    for(let i=0; i<errorTexts.length; i++){
        errorTexts[i].innerText = "";
    }
    errorInfo.innerText = "";
}

function checkRequired(value) {
    if (!value) {
        return false;
    }
    value = value.toString().trim();
    if(value === "" || value === "-- Wybierz kurs--" || value === "-- Wybierz użytkownika--"){
        return false;
    }
    return true;
}

function checkTextLengthRange(value, min, max){
    if (!value){
        return false;
    }
    value = value.toString().trim();
    const length = value.length;
    if (max && length > max) {
        return false;
    }
    if (min && length < min){
        return false;
    }
    return true;
}

function checkEmail(value) {
    if (!value){
        return false;
    }
    value = value.toString().trim();
    const re = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return re.test(value);
}

function checkDate(value) {
    var today = Date.now();
    var d2 = new Date(value);

    return d2 < today;
}

function checkPrice(value){
    value = parseInt(value.toString().trim());
    return value % 5 == 0 && value % 10 == 0;
}

function checkMinPrice(value){
    value = parseInt(value.toString().trim());
    return value >= 200;
}

function checkExpiringDate(expiring, signup){
    if(!expiring || !signup){
        return false;
    }
    if(!checkDateFormat(expiring) || !checkDateFormat(signup)){
        return false;
    }
    const expiringDate = new Date(expiring);
    const signupDate = new Date(signup);
    if(expiringDate.getTime() < signupDate.getTime()){
        return false;
    }
    return true;
}

function checkDateFormat(value){
    if(!value){
        return false;
    }
    const pattern = /(\d{4})-(\d{2})-(\d{2})/;
    return pattern.test(value);
}
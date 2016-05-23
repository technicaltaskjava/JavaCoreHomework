var messageErrorClass = 'message-err',
    fieldErrorClass = 'field-err';

function validateUserName(value) {
    var regexp = /^[a-zA-Z'][a-zA-Z-' ]+[a-zA-Z']?$/;
    return (value && regexp.test(value));
}

function validateYear(value) {
    var numYear = parseInt(value);
    var currYear = new Date().getFullYear();
    return (numYear && (currYear - numYear) >= 16 && (currYear - numYear) <= 100);
}

function validateEmail(value) {
    var regexp = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;
    return (value && regexp.test(value));
}

function validatePassword(value) {
    var regexp = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
    return (value && regexp.test(value));
}

function validatePassword2(value, valueCompare) {
    return (value && valueCompare && value === valueCompare);
}

function hideError(elem) {
    $(elem).removeClass(fieldErrorClass);

    var nextElement = $(elem).next()[0];
    if (nextElement.className === messageErrorClass) {
        nextElement.remove();
    }
}

function showError(elem, message) {
    $(elem).addClass(fieldErrorClass);

    var nextElement = $(elem).next()[0];
    if (nextElement.className !== messageErrorClass) {
        $(elem).after('<div class="' + messageErrorClass + '">' + message + '</div>');
    }
}

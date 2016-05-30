$(function () {
    var form = $('#slick-login'),
        fields = {
            email: form.find('#email'),
            nick: form.find('#nick'),
            age: form.find('#age'),
            password: form.find('#password'),
			password2: form.find('#password2')
        },
        submitButton = form.find('input[type="submit"]'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'email',
                callback: validateEmail,
                message: 'Invalid email'
            }, {
                field: 'nick',
                callback: validateNick,
                message: 'Invalid nick (must contain at least 3 symbols - letters and digits)'
            }, {
                field: 'age',
                callback: validateAge,
                message: 'Invalid age (3 - 100)'
            }
			, {
                field: 'password',
                callback: validatePassword,
                message: 'Password must contain at least one number, one lowercase and one uppercase letter'
            }
			, {
                field: 'password2',
                callback: validateConfirm,
                message: 'Passwords do not match'
            }
        ];

    function validateNick(value) {
       var regexp = /^[a-zA-Z0-9]+$/;
        if (value && regexp.test(value) && value.length > 2) {
            return true;
        }

        return false;
    }

    function validateAge(value) {
        var numAge = parseInt(value);

        if (!numAge || numAge < 3 || numAge > 100) {
            return false;
        }

        return true;
    }

    function validateEmail(value) {
        var regexp = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;

        if (value && regexp.test(value)) {
            return true;
        }

        return false;
    }
	  function validatePassword(value) {
        var regexp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;

        if (value && regexp.test(value)) {
            return true;
        }

        return false;
    }
	function validateConfirm(){

    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('password2');
    if(pass1.value === pass2.value){
        return true;
    }
	 return false;}

    submitButton.on('click', function (e) {
        e.preventDefault();
        errors.empty();

        var allIsValid = true;
        validations.forEach(function (el) {
            if ( !el.callback(fields[el.field].val()) ) {
                errors.append('<div>' + el.message + '</div>');
				errors.append('-----------------------------------------');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
        }
    });
});

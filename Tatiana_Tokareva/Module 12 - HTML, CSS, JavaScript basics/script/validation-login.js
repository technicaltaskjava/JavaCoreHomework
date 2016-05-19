$(function () {
    var form = $('.login'),
        fields = {
            email: form.find('.email'),
            password: form.find('.password'),
        },
        submitButton = form.find('.enter[type="submit"]'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'email',
                callback: validateEmail,
                message: 'Invalid email'
            }, {
                field: 'password',
                callback: validatePassword,
                message: 'password must contains minimum 8 characters at least 1 number.'
            },
        ];

    

    function validateEmail(value) {
        var regexp = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;

        if (value && regexp.test(value)) {
            return true;
        }

        return false;
    }

		 function validatePassword(value) {
        var regexp = /^(?=.*\d)(?=.*[a-zа-я])[0-9a-zA-Zа-я]{8,}$/;

        if (value && regexp.test(value)) {
            return true;
        }

        return false;
    }
		
		
    submitButton.on('click', function (e) {
        e.preventDefault();
        errors.empty();

        var allIsValid = true;
        var index = validations.length - 1;

			
				
        validations.forEach(function (el) {
            if ( !el.callback(fields[el.field].val()) ) {
                errors.append('<p>' + el.message + '</p>');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
						
	
        }
				
				
    });
		
		
});
	
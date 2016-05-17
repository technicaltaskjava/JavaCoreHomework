$(function () {
    var form = $('.registration'),
        fields = {
						lastName: form.find('.last-name'),
						firstName: form.find('.first-name'),
            email: form.find('.email'),
            yearBirth: form.find('.year_birth'),
            password: form.find('.password'),
            
           
	        },
        submitButton = form.find('.register[type="submit"]'),
        errors = form.find('.errors'),
        validations = [
				{
                field: 'lastName',
                callback: validateLastName,
                message: 'Invalid first name (can\'t be empty)'
            },
						{
                field: 'firstName',
                callback: validateFirstName,
                message: 'Invalid first name (can\'t be empty)'
            },
            {
                field: 'email',
                callback: validateEmail,
                message: 'Invalid email'
            }, {
                field: 'yearBirth',
                callback: validateYearBirth,
                message: 'input correct year'
            }	,{
                field: 'password',
                callback: validatePassword,
                message: 'password must contains minimum 8 characters at least 1 number.'
            },
					
        ];

    

		 function validateLastName(value) {
        if (value && value.length > 3) {
            return true;
        }

        return false;
    }
		
		 function validateFirstName(value) {
        if (value && value.length > 2) {
            return true;
        }

        return false;
    }
		
		 function validateYearBirth(value) {
        var yearBirth = parseInt(value);

        if (!yearBirth || yearBirth < 1920 || yearBirth > 2016) {
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
	
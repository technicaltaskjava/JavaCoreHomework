$(document).ready(function () {
    var signInForm = $('.signIn'),
        welcome = $('.welcome'),
        signInButton = $('#signIn'),
        regButtonInSignInForm = $('#regSignButton'),
        errors = $('.errors'),
        signOutButton = $('.signOut'),
        registrationForm = $('.reg'),
        regButtonInRegForm = $('#regButton'),
        predictions = $('.predictions'),
        predictionsParagraph = predictions.find('p'),
        openCookieButton = predictions.find('button[type="submit"]'),
        fieldsSignInForm = {
            login: signInForm.find('#login'),
            pass: signInForm.find('#pass')
        },
        validationsSignInForm = [
            {
                field: 'pass',
                callback: validatePass,
                message: 'Invalid pass'
            }, {
                field: 'login',
                callback: validateLogin,
                message: 'Invalid login'
            }
        ],
        fieldsRegForm = {
            login: registrationForm.find('#loginReg'),
            pass: registrationForm.find('#passReg'),
            confirmPass: registrationForm.find('#confirmPass'),
            email: registrationForm.find('#email')
        },
        validationsRegForm = [
            {
                field: 'login',
                callback: validateLogin,
                message: 'Invalid login'
            }, {
                field: 'pass',
                callback: validatePass,
                message: 'Invalid pass'
            }, {
                field: 'confirmPass',
                callback: validatePass,
                message: 'Invalid confirm pass'
            }, {
                field: 'email',
                callback: validateEmail,
                message: 'Invalid email'
            }
        ];

    $(function(){
        $.post('/controller',
            function (data) {
                if (data.length < 40) {
                    welcome.text("Welcome, " + data);
                    signInForm.hide();
                    signOutButton.show();
                }
            });
    });

    function validateEmail(value) {
        var regexp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return value && regexp.test(value);
    }

    function validateLogin(value) {
        var regexp = /^[a-zA-Z0-9_-]{3,20}$/;
        return value && regexp.test(value);
    }

    function validatePass(value) {
        var regexp = /^[a-zA-Z0-9]{4,20}$/;
        return value && regexp.test(value);
    }

    regButtonInSignInForm.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        registrationForm.show();
        signInForm.hide();
    });

    signInButton.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        welcome.empty();
        var allIsValid = true;
        validationsSignInForm.forEach(function (el) {
            if (!el.callback(fieldsSignInForm[el.field].val())) {
                errors.append('<p>' + el.message + '</p>');
                allIsValid = false;
            }
        });
        if (allIsValid) {
            $.post('/signIn',
                {
                    login: fieldsSignInForm.login.val(),
                    pass: fieldsSignInForm.pass.val()
                },
                function (data) {
                    if (data.length < 10) {
                        welcome.text("Welcome, " + fieldsSignInForm.login.val());
                        signInForm.hide();
                        signOutButton.show();
                    } else {
                        var mes = data.split(".");
                        for (var i = 0; i < mes.length; i++) {
                            errors.append('<p>' + mes[i] + '</p>');
                        }
                    }
                });
        }
    });

    signOutButton.on('click', function (e) {
        e.preventDefault();
        $.post('/controller',
            {
                signOut: "exit"
            });
        location.reload(true);
    });

    regButtonInRegForm.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        var regFormIsValid = true;
        validationsRegForm.forEach(function (el) {
            if (!el.callback(fieldsRegForm[el.field].val())) {
                errors.append('<p>' + el.message + '</p>');
                regFormIsValid = false;
            }
        });
        if (fieldsRegForm.pass.val() !== fieldsRegForm.confirmPass.val()) {
            regFormIsValid = false;
            errors.append('<p>Password and confirm password are not same.</p>');
        }
        if (regFormIsValid) {
            $.post('/reg',
                {
                    login: fieldsRegForm.login.val(),
                    pass: fieldsRegForm.pass.val(),
                    confirmPass: fieldsRegForm.confirmPass.val(),
                    email: fieldsRegForm.email.val()
                },
                function (data) {
                    if (data === "") {
                        welcome.text("Registration complete! Enter login and password:");
                        signInForm.show();
                        registrationForm.hide();
                        regButtonInSignInForm.hide();
                    } else {
                        var mes = data.split(".");
                        for (var i = 0; i < mes.length; i++) {
                            errors.append('<p>' + mes[i] + '</p>');
                        }
                    }
                });
        }
    });

    openCookieButton.click(function (e) {
        e.preventDefault();
        $.post('/cookie',
            function (data) {
                predictionsParagraph.text(data);
            });
        predictionsParagraph.fadeIn(3000);
        predictionsParagraph.fadeOut(3000);
    });

});
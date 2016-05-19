$(function () {
    var signeInForm = $('.signeIn'),
        fieldsSigneInForm = {
            login: signeInForm.find('#login'),
            pass: signeInForm.find('#pass')
        },
        validationsSigneInForm = [
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
        signeInButton = $('#signeIn'),
        regButtonInSigneInForm = $('#regSignButton'),
        errors = $('.errors'),
        signeOutButton = $('.signeOut'),
        registrationForm = $('.reg'),
        regButtonInRegForm = $('#regButton'),
        fieldsRegForm = {
            login: registrationForm.find('#loginReg'),
            pass: registrationForm.find('#passReg'),
            confirmPass: registrationForm.find('#confirmPass'),
            email: registrationForm.find('#email')
        },
        welcome = $('.welcome'),
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
        ],
        users = [
            {
                login: 'admin',
                pass: 'admin',
                email: 'admin@admin.com'
            }, {
                login: 'user',
                pass: 'user',
                email: 'user@user.com'
            }
        ],
        predictions = $('.predictions'),
        predictionsParagraph = predictions.find('p'),
        openCookieButton = predictions.find('button[type="submit"]'),
        predictionsText = [
            'Follow that restless urge to find yourself.',
            'Begin trusting your intuitions.',
            'Ask a friend to join you on your next journey.',
            'Shoot for the moon! If you miss you will still be amongst the stars.',
            'Good things are on their way.',
            'Today is the first day of the rest of your life.',
            'The big issues are career, status or work right now.',
            'Your good nature will bring you unbounded happiness.',
            'Your greatest fortune is the friends and family you have.',
            'It takes ten times as many muscles to frown as it does to smile.',
            'An unexpected aquaintance will resurface.',
            'Embrace change; do not fight it.',
            'A golden egg of opportunity falls into your lap this week.',
            'Try everything once, even the things you don\'t think you will like.',
            'Wisdom comes from experience.',
            'An unexpected relationship will become permanent.',
            'Now is the time to try something new.',
            'You will be called upon to help a friend in trouble.',
            'You will be an inspiration to others.',
            'Conquer your fears, or they will conquer you.',
            'In order to have great friends, you must first learn to be a great friend.'
        ],
        index = 0,
        tableDiv = $('.table'),
        table = tableDiv.find('table'),
        delPredButton = $('#delPred'),
        insPredButton = $('#insPred'),
        editPredButton = $('#editPred'),
        sendPredButton = $('#sendPred'),
        editPredTextArea = $('#textEditPred'),
        deleteButton = $('#delete'),
        editButton = $('#edit'),
        numPred = $('#numPred');
        

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

    function authorization() {
        for (var i = 0; i < users.length; i++) {
            if (users[i]['login'] === fieldsSigneInForm.login.val()) {
                return (users[i]['pass'] === fieldsSigneInForm.pass.val());
            }
        }
    }

    function isLoginExists(login) {
        for (var i = 0; i < users.length; i++) {
            if (users[i]['login'] === login) {
                return true;
            }
        }
        return false;
    }

    function isEmailExists(email) {
        for (var i = 0; i < users.length; i++) {
            if (users[i]['email'] === email) {
                return true;
            }
        }
        return false;
    }

    function createTable() {
        for (var i = 1; i < predictionsText.length + 1; i++) {
            table.append('<tr>' +
                    '<td>' + i + '</td>' +
                    '<td>' + predictionsText[i - 1] + '</td>' +
                    '</tr >');
        }
    }
    
    function setNumberPredictionAttr() {
        if(predictionsText.length > 0) {
            numPred.attr('min', 1);
            numPred.attr('max', predictionsText.length);
            numPred.attr('step', 1);
        } else {
            errors.append('<p>There are no predictions</p>');
        }
    }

    function reCreateTable() {
        errors.empty();
        table.empty();
        createTable();
    }
    
    function hideDelInsEdit() {
        insPredButton.hide();
        delPredButton.hide();
        editPredButton.hide();
    }
    
    function showDelInsEdit() {
        insPredButton.show();
        delPredButton.show();
        editPredButton.show();
    }

    regButtonInSigneInForm.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        registrationForm.show();
        signeInForm.hide();
    });

    signeInButton.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        welcome.empty();
        var allIsValid = true;
        validationsSigneInForm.forEach(function (el) {
            if (!el.callback(fieldsSigneInForm[el.field].val())) {
                errors.append('<p>' + el.message + '</p>');
                allIsValid = false;
            }
        });
        if (allIsValid) {
            registrationForm.hide();
            if (authorization()) {
                welcome.append('<p> Hello, ' + fieldsSigneInForm.login.val() + '! </p>');
                signeOutButton.show();
                signeInForm.hide();
                tableDiv.show();
                createTable();
            } else {
                errors.append('<p>Authorization error!</p>');
                signeInForm.show();
            }
        }
    });

    regButtonInRegForm.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        var regFormIsValid = true,
                regDataIsValid = true;
        validationsRegForm.forEach(function (el) {
            if (!el.callback(fieldsRegForm[el.field].val())) {
                errors.append('<p>' + el.message + '</p>');
                regFormIsValid = false;
            }
        });
        if (regFormIsValid) {
            if (fieldsRegForm.pass.val() !== fieldsRegForm.confirmPass.val()) {
                regDataIsValid = false;
                errors.append('<p>Password and confirm password are not same.</p>');
            }
            if (isLoginExists(fieldsRegForm.login.val())) {
                regDataIsValid = false;
                errors.append('<p>Such login is already used.</p>');
            }
            if (isEmailExists(fieldsRegForm.email.val())) {
                regDataIsValid = false;
                errors.append('<p>Such email is already used.</p>');
            }
            if (regDataIsValid) {
                users.push({login: fieldsRegForm.login.val(),
                    pass: fieldsRegForm.pass.val(),
                    email: fieldsRegForm.email.val()});
                welcome.append('<p>' + 'Congratulations, you have successfully registered! <br>' +
                        ' Enter login and password:' + '</p>');
            }
            signeInForm.show();
            registrationForm.hide();
        }
    });

    openCookieButton.on('click', function (e) {
        e.preventDefault();
        var randomIndex = Math.floor(Math.random() * predictionsText.length);
        predictionsParagraph.text(predictionsText[randomIndex]);
        predictionsParagraph.fadeIn(3000);
        predictionsParagraph.fadeOut(3000);
    });

    delPredButton.on('click', function (e) {
        e.preventDefault();
        setNumberPredictionAttr();
        numPred.show();
        hideDelInsEdit();
        deleteButton.show();
    });
    
    deleteButton.on('click', function (e) {
        e.preventDefault();
        predictionsText.splice(numPred.val() - 1, 1);
        reCreateTable();
        numPred.hide();
        showDelInsEdit();
        deleteButton.hide();
    });

    insPredButton.on('click', function (e) {
        e.preventDefault();
        index = 0;
        editPredTextArea.empty();
        hideDelInsEdit();
        sendPredButton.show();
        editPredTextArea.show();
        editPredTextArea.append('<textarea rows="2" cols="45"></textarea>');
    });
    
    editPredButton.on('click', function (e) {
        e.preventDefault();
        setNumberPredictionAttr();
        numPred.show();
        editButton.show();
        hideDelInsEdit();
    });
    
    editButton.on('click', function (e) {
        e.preventDefault();
        sendPredButton.show();
        editPredTextArea.show();
        editPredTextArea.append('<textarea rows="2" cols="45"></textarea>');
        editPredTextArea.find('textarea').text(predictionsText[numPred.val() - 1]);
        index = numPred.val();
        numPred.hide();
        editButton.hide();
    });
     
   sendPredButton.on('click', function (e) {
        e.preventDefault();
        if(index === 0) {
            predictionsText.push(editPredTextArea.find('textarea').val());
        } else {
            predictionsText[index - 1] = editPredTextArea.find('textarea').val();
        }
        editPredTextArea.empty();
        showDelInsEdit();
        sendPredButton.hide();
        editPredTextArea.hide();
        reCreateTable();
    });
});
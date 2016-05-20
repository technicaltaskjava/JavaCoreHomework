function User(id, login, firstName, secondName, password, email, regDate) {
    this.id = id;
    this.login = login;
    this.firstName = firstName;
    this.secondName = secondName;
    this.password = password;
    this.email = email;
    this.regDate = regDate;
}

var users = [
    new User(1, "login1", "firstName01", "secondName01", "password01", "email01@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(2, "login2", "firstName02", "secondName02", "password02", "email02@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(3, "login3", "firstName03", "secondName03", "password03", "email03@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(4, "login4", "firstName04", "secondName04", "password04", "email04@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(5, "login5", "firstName05", "secondName05", "password05", "email05@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(6, "login6", "firstName06", "secondName06", "password06", "email06@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(7, "login7", "firstName07", "secondName07", "password07", "email07@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(8, "login8", "firstName08", "secondName08", "password08", "email08@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(9, "login9", "firstName09", "secondName09", "password09", "email09@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(10, "login10", "firstName10", "secondName10", "password10", "email10@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30))),
    new User(11, "login11", "greedyFirstName", "greedySecondName", "greedyPassword11", "greedy@gmail.com", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)))
];

var cookie = {
    id: 0,
    cookie: "",
    expirationDate: new Date(),
    price: 1.00
};

function Cookie(id, cookie, expirationDate, price) {
    this.id = id;
    this.cookie = cookie;
    this.expirationDate = expirationDate;
    this.price = price;
}

var cookies = [
    new Cookie(55555, "You are a person of culture, cultivate it", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "You will be an inspiration to others", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "Take the time to be considerate of others", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "It is not advisable to leap before you look, but that may be all you have time for", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "This is the time for caution, but not for fear", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "Do what you can to prolong your life, in the hope that someday you'll learn what it's for", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "Demonstrate refinement in everything you do", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "Pursue your work with all due seriousness", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "Opportunity is knocking at your front door", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
    new Cookie(55555, "Try everything once, even the things you don't think you will like", new Date(2016, Math.floor(Math.random() * 12), Math.floor(Math.random() * 30)), (Math.random() * 10).toFixed(2)),
];

function isLoginInDatabase(login) {
    for (var i = 0; i < users.length; i++) {
        if (users[i].login === login) {
            return true;
        }
    }

    return false;
}

function isPasswordInDatabase(password) {
    for (var i = 0; i < users.length; i++) {
        if (users[i].password === password) {
            return true;
        }
    }

    return false;
}

function isEmailInDatabase(email) {
    for (var i = 0; i < users.length; i++) {
        if (users[i].email === email) {
            return true;
        }
    }

    return false;
}

function deleteCookieByIndex(index) {
    cookies[index] = undefined;

    var newCookies = [];

    for (i = 0; i < cookies.length; i++) {
        if (cookies[i]) {
            newCookies.push(cookies[i]);
        }
    }

    cookies = newCookies;
}

function setPredicationToCookie(index, predication) {
    if (cookies[index]) {
        cookies[index].cookie = predication;
    } else {
        cookies.push(new Cookie(cookies.length + 1, predication, new Date, 1.00));
    }


}




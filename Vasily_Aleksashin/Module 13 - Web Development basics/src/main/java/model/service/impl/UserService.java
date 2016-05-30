package model.service.impl;

import dao.impl.UserDao;
import model.entity.User;
import model.service.AbstractEntityService;
import utility.Validator;

import java.util.List;

public class UserService extends AbstractEntityService<User, UserDao> {

    public UserService(final UserDao userDao) {
        super(userDao);
    }

    public User create(final int age, final String... data) {
        Validator.isNull(data);
        if (data.length < 5) {
            throw new IllegalArgumentException("User data is not complete");
        }
        for (String element : data) {
            Validator.isNull(element);
        }
        User user = new User();
        user.setUserName(data[0]);
        user.setEmail(data[1]);
        user.setPassword(data[2]);
        user.setFirstName(data[3]);
        user.setLastName(data[4]);
        user.setAge(age);
        return add(user);
    }

    public User getByLogin(final String login) {
        final List<User> users = getAll();
        for (User user : users) {
            if (login.equals(user.getUserName())) {
                return user;
            }
        }
        return null;
    }
}

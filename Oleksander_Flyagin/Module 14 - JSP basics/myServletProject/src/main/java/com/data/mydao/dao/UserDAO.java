package com.data.mydao.dao;


import com.data.users.User;

import java.util.List;

public interface UserDAO {


    User findForLogin(String login);
    User findForLoginPassword(String login, String password);
    void create(User user);
    void update(User user);
    void delete(User user);
    boolean existLogin(String login);
    List<User> usersList();
    int countUsers();



}

package com.epam.mydao.dao;


import com.epam.mydao.user.Users;



public interface UserDAO {
     Users findForID(int id);
     Users findForEmailPassword(String email, String password);
     void create(Users user);
     void update(Users user);
     void delete(Users user);
     boolean existEmail(String email);
     void changePassword(Users users);
     int getID(Users users);




}

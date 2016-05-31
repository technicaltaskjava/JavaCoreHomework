package com.data.mydao.dao;


import com.data.cookie.Cookie;

import java.util.List;

public interface CookieDAO {
    Cookie findForID(int id);
    Cookie findForIdlPrediction(int id, String pediction);
    void create(Cookie cookie);
    void addPrediction(String cookie);
    void update(Cookie cookie);
    void delete(int id);
    boolean existID(int id);
    void changePrediction(Cookie cookie);
    int getID(Cookie cookie);
    List<Cookie> cookieList();
    int countPrediction();


}

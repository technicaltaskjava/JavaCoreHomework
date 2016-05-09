package com.epam.dao.dao;

import com.epam.dao.objects.Cookie;
import com.epam.dao.objects.Metadata;
import com.epam.dao.objects.User;

import java.util.List;

public interface Dao {
    public List<User> getAllUsers();
    public List<Cookie> getAllCookies();
    public List<Metadata> getAllMetadata();

    public User getUser(int id);
    public Cookie getCookie(int id);

    public List<User> getUserByName(String userName);
    public List<Cookie> findCookieByText(String text);
    public List<Metadata> findMetadata(int userId, int cookieId);

    public void addUser(User user);
    public void addCookie(Cookie cookie);
    public void addMetadata(Metadata metadata);

    public void deleteUser(int id);
    public void deleteCookie(int id);

    public void closePool();
}

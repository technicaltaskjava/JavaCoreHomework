package com.epam.mydao;

import com.epam.mydao.mypool.Commands;
import com.epam.mydao.user.Users;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private Main() {
    }

    public static void main(String[] args) {


        List<Users> users = new ArrayList();
        users.add(new Users("000665", "cen@on.com"));
        users.add(new Users("7666585", "vov@on.com"));
        users.add(new Users("77777777", "sem@on.com"));
        users.add(new Users("9999999999", "devi@on.com"));
        users.add(new Users("645745537", "cenza@on.com"));

        Commands com = new Commands(users);
        com.connection();






    }
}

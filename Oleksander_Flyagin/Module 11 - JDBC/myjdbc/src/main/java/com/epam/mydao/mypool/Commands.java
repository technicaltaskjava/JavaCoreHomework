package com.epam.mydao.mypool;


import com.epam.mydao.exeptions.MyJDBCException;
import com.epam.mydao.user.Users;
import java.util.List;



public class Commands {
    private   List<Users>  users;
    private  PoolThread poolThread;
    private  MyConnectionPool pool = new MyConnectionPool(10);


    public Commands(List<Users> users)
    {
        this.users = users;
    }
    private  int poolSiz()
    {
        return users.size();
    }


    public void connection()  {

        for (int start =0; start<poolSiz(); start++)
        {
            try {
                poolThread =  new  PoolThread(pool.retrieve(),users.get(start));
                poolThread.join();
                pool.putBack(pool.retrieve());
            } catch (Exception e) {
                throw  new MyJDBCException(e);

            }

        }
    }

}

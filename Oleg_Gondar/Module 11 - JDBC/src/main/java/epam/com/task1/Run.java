package epam.com.task1;

import java.sql.SQLException;

public class Run {
    private Run() {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        WorkWithDB.updateDataInDB("UpdatedLastName2", "UpdatedFirstName2", "UpdatedAddress2", "user2");
        WorkWithDB.updateDataInDB("UpdatedLastName3", "UpdatedFirstName3", "UpdatedAddress3", "user3");
        WorkWithDB.insertDataInDB();
        WorkWithDB.getAllDataFromDB();
        WorkWithDB.getSomeData("user2");
        WorkWithDB.deleteTable();
    }
}

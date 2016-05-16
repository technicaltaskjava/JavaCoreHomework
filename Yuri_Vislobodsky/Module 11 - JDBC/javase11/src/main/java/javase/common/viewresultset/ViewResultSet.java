package javase.common.viewresultset;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * View any Result Set as a screen table with column headers
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class ViewResultSet {
    private ViewResultSet() {}

    public static void view(ResultSet rs) throws SQLException {
        System.out.println();
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int columnCount = rsMetaData.getColumnCount();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            String formatSpecifier = "%-" + rsMetaData.getColumnDisplaySize(columnIndex) + "s ";
            System.out.print(String.format(formatSpecifier, rsMetaData.getColumnName(columnIndex)));
        }
        System.out.println();
        while (rs.next()) {
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                String formatSpecifier = "%-" + rsMetaData.getColumnDisplaySize(columnIndex) + "s ";
                System.out.print(String.format(formatSpecifier, rs.getString(columnIndex)));
            }
            System.out.println();
        }
    }
}

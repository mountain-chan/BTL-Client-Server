package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLyKhachSanDb;integratedSecurity=true;");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

}

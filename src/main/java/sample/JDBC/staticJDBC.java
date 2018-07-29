package sample.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class staticJDBC {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/computers?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "147258369aA!q";

    public static Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return connection;
    }
}

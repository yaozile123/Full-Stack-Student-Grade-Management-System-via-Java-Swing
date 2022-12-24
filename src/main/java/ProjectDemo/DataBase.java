package ProjectDemo;

import java.sql.*;

public class DataBase {
    private static String url = "jdbc:mysql://localhost:3306/management?useSSL=true";
    private static String userName = "root";
    private static String passWord = "Ss705062.";
    private static String driver= "com.mysql.jdbc.Driver";


    public static Connection getConnect(){
        try {
            Connection connection = DriverManager.getConnection(url,userName,passWord);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnect(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeResult(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeStatement(Statement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

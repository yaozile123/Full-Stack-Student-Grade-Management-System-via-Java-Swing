package ProjectDemo.Service;

import ProjectDemo.DataBase;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AdminService implements AdminInterface {
    String username;
    String password;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public AdminService(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validate() {
        conn = DataBase.getConnect();
        String sql = "select * from user_info" + " where username = ? and password = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeResult(resultSet);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeConnect(conn);
        }
    }

    @Override
    public boolean register() {
        conn = DataBase.getConnect();
        String sql = "select * from user_info" + " where username = ? and password = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeResult(resultSet);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeConnect(conn);
        }
    }

    @Override
    public boolean resetPassword() {
        conn = DataBase.getConnect();
        String sql = "select * from user_info" + " where username = ? and password = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){

                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeResult(resultSet);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeConnect(conn);
        }
    }

    public boolean testDuplicate(){
        conn = DataBase.getConnect();
        String sql = "select username from user_info" + " where username = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeResult(resultSet);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeConnect(conn);
        }
    }
}

package ProjectDemo.handler;

import ProjectDemo.DataBase;
import ProjectDemo.registerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterHandler implements ActionListener {
    private registerView regview;
    private Connection conn = null;
    private  PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public RegisterHandler(registerView regview) {
        this.regview = regview;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sign Up")) {
            String userName = regview.getUserNameTxt().getText();
            String passWord = regview.getPassWordTxt().getText();
            String confirm = regview.getConfirmTxt().getText();
            if ("".equals(userName.trim()) || "".equals(passWord.trim())|| "".equals(confirm.trim())){
                JOptionPane.showMessageDialog(regview,"Please enter valid username/password");
                return;
            }
            if (!userName.equals(passWord)){
                JOptionPane.showMessageDialog(regview,"Password do not match");
            }
            else {
                if (valid()) {
                    register();
                    JOptionPane.showMessageDialog(regview, "Register Success!");
                    regview.dispose();
                } else {
                    JOptionPane.showMessageDialog(regview, "Username already existed!");
                }
            }
        } else if (e.getActionCommand().equals("Reset")) {
            regview.getUserNameTxt().setText("");
            regview.getPassWordTxt().setText("");
            regview.getConfirmTxt().setText("");
        }
    }

    public boolean valid(){
        String username = regview.getUserNameTxt().getText();
        String sql = "Select * from user_info where username = ?";
        conn = DataBase.getConnect();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return false;
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DataBase.closeConnect(conn);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeResult(resultSet);
        }
    }

    public void register(){
        String username = regview.getUserNameTxt().getText();
        String password = regview.getPassWordTxt().getText();
        String sql = "insert into user_info (username,password) Values(?,?)";

        try {
            conn = DataBase.getConnect();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            int i = preparedStatement.executeUpdate();
            if (i <=0 ){
                System.out.println("Something went wrong");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeConnect(conn);
            DataBase.closeStatement(preparedStatement);
        }
    }
}

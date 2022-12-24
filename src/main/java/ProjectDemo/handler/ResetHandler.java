package ProjectDemo.handler;

import ProjectDemo.DataBase;
import ProjectDemo.resetView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetHandler implements ActionListener {
    private resetView view;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public ResetHandler(resetView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Confirm")){
            String userName = view.getUserNameTxt().getText();
            String passWord = view.getPassWordTxt().getText();
            String newpassWord = view.getNewTxt().getText();
            if ("".equals(userName.trim()) || "".equals(passWord.trim())|| "".equals(newpassWord.trim())){
                JOptionPane.showMessageDialog(view,"Please enter valid username/password");
                return;
            }
            if (!check(userName,passWord)){
                JOptionPane.showMessageDialog(view,"Username and Password do not match");
                return;
            }
            update(userName,newpassWord);
            JOptionPane.showMessageDialog(view,"Reset Password Successfully!");
            view.dispose();
        } else if (e.getActionCommand().equals("Reset")) {
            view.getUserNameTxt().setText("");
            view.getPassWordTxt().setText("");
            view.getNewTxt().setText("");
        }
    }
    private void update(String userName,String newpassWord){
        String sql = "Update user_info Set password = ? where username = ?";
        conn = DataBase.getConnect();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(2,userName);
            preparedStatement.setString(1,newpassWord);
            int i = preparedStatement.executeUpdate();
            if (i <= 0) {
                System.out.println("sth went wrong");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeConnect(conn);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeResult(resultSet);
        }


    }
    private boolean check(String userName,String passWord){
        conn = DataBase.getConnect();
        String sql = "Select * from user_info where username = ? and password = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,passWord);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;}
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeConnect(conn);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeResult(resultSet);
        }

    }
}

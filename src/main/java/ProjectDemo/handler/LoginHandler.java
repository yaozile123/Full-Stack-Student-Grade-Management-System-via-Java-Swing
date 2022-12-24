package ProjectDemo.handler;

import ProjectDemo.LoginView;
import ProjectDemo.MainView;
import ProjectDemo.Service.AdminService;
import ProjectDemo.registerView;
import ProjectDemo.resetView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    LoginView loginView;
    resetView resView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")){
            loginCheck();
        } else if (e.getActionCommand().equals("Register")) {
            new registerView();

        } else if (e.getActionCommand().equals("Reset Password")) {
            resView = new resetView();
        }
    }

    private void loginCheck() {
        String userName = loginView.getUserNameTxt().getText();
        String passWord = loginView.getPasswordTxt().getText();
        System.out.println(userName + ":"+passWord);
        if ("".equals(userName.trim()) || "".equals(passWord.trim())){
            JOptionPane.showMessageDialog(loginView,"Please enter valid username/password");
            return;
        }
        AdminService user = new AdminService(userName,passWord);
        boolean flag = user.validate();
        if (flag){
            JOptionPane.showMessageDialog(loginView, "Login Success!");
            new MainView();
            loginView.dispose();
        }else{
            JOptionPane.showMessageDialog(loginView,"Username and Password do not match");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            loginCheck();
        }
    }
}

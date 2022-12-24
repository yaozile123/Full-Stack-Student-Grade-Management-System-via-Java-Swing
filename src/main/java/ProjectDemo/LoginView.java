package ProjectDemo;

import ProjectDemo.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    JLabel title = new JLabel("Student Grade Management System");
    JLabel userNameLabel = new JLabel("username:");
    JLabel passwordLabel = new JLabel("password:");

    JButton loginButton= new JButton("Login");
    JButton registerButton = new JButton("Register");
    JButton resetButton = new JButton("Reset Password");
    JTextField userNameTxt = new JTextField();
    JPasswordField passwordTxt = new JPasswordField();
    SpringLayout layout = new SpringLayout();

    JPanel panel = new JPanel(layout);
    LoginHandler loginHandler;

    public LoginView(){
        super("Welcome");
        loginHandler = new LoginHandler(this);
        Container container = this.getContentPane();
        setLayout(container);
        loginButton.addActionListener(loginHandler);
        loginButton.addKeyListener(loginHandler);
        registerButton.addActionListener(loginHandler);
        resetButton.addActionListener(loginHandler);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // set loginButton as the default button
        this.getRootPane().setDefaultButton(loginButton);
        // set the windown location to be the center of our screen
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setBounds(screenWidth/3,screenHeight/3,screenWidth/3,screenHeight/3);
        this.setResizable(true);
        this.setVisible(true);
    }

    private void setLayout(Container container) {
        // title centralized
        int titleoffset = Spring.width(title).getValue() / 2;
        layout.putConstraint(SpringLayout.WEST,title,-titleoffset,SpringLayout.HORIZONTAL_CENTER,panel);
        SpringLayout.Constraints usrConstraints = layout.getConstraints(userNameLabel);

        // set size for textfields
        userNameTxt.setPreferredSize(new Dimension(200,30));
        passwordTxt.setPreferredSize(new Dimension(200,30));

        //set location for username label
        Spring usrWidth = Spring.width(userNameLabel);
        Spring usrTxtWidth = Spring.width(userNameTxt);
        Spring childWidth = Spring.sum(Spring.sum(usrWidth,usrTxtWidth),Spring.constant(20));
        int offsetUsr = childWidth.getValue()/2;
        layout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetUsr,SpringLayout.HORIZONTAL_CENTER,panel);
        usrConstraints.setY(Spring.constant(60));

        // set location for textfields
        layout.putConstraint(SpringLayout.WEST,userNameTxt,20,SpringLayout.EAST,userNameLabel);
        layout.putConstraint(SpringLayout.NORTH,userNameTxt,-5,SpringLayout.NORTH,userNameLabel);
        layout.putConstraint(SpringLayout.WEST,passwordLabel,0,SpringLayout.WEST,userNameLabel);
        layout.putConstraint(SpringLayout.NORTH,passwordLabel,33,SpringLayout.SOUTH,userNameLabel);
        layout.putConstraint(SpringLayout.WEST,passwordTxt,0,SpringLayout.WEST,userNameTxt);
        layout.putConstraint(SpringLayout.NORTH,passwordTxt,20,SpringLayout.SOUTH,userNameTxt);

        //set location for login/reset/register button
        Spring regWidth = Spring.width(registerButton);
        Spring logWidth = Spring.width(loginButton);
        Spring resetWidth = Spring.width(resetButton);
        Spring bottomWidth = Spring.sum(Spring.sum(Spring.sum(Spring.constant(20),regWidth),logWidth),resetWidth);
        int botOffset = bottomWidth.getValue()/2;
        layout.putConstraint(SpringLayout.WEST,loginButton,-botOffset,SpringLayout.HORIZONTAL_CENTER,panel);
        SpringLayout.Constraints loginConstraints = layout.getConstraints(loginButton);
        loginConstraints.setY(Spring.constant(180));
        layout.putConstraint(SpringLayout.WEST,registerButton,10,SpringLayout.EAST,loginButton);
        layout.putConstraint(SpringLayout.NORTH,registerButton,0,SpringLayout.NORTH,loginButton);
        layout.putConstraint(SpringLayout.WEST,resetButton,10,SpringLayout.EAST,registerButton);
        layout.putConstraint(SpringLayout.NORTH,resetButton,0,SpringLayout.NORTH,registerButton);

        // add component to panel
        panel.add(title);
        panel.add(userNameLabel);
        panel.add(userNameTxt);
        panel.add(passwordLabel);
        panel.add(passwordTxt);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(resetButton);
        container.add(panel);
    }

    public JTextField getUserNameTxt() {
        return userNameTxt;
    }

    public JTextField getPasswordTxt() {
        return passwordTxt;
    }

    public static void main(String[] args) {
        new LoginView();
    }
}

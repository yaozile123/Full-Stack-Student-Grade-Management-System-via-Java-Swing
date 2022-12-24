package ProjectDemo;

import ProjectDemo.handler.RegisterHandler;

import javax.swing.*;
import java.awt.*;

public class registerView extends JFrame {
    JLabel title = new JLabel("Register");
    JLabel userNameLabel = new JLabel("username:");
    JLabel passwordLabel = new JLabel("password:");
    JLabel confirmLabel = new JLabel("confirm password:");
    JTextField userNameTxt = new JTextField();
    JTextField passWordTxt = new JTextField();
    JTextField confirmTxt = new JTextField();
    JButton signUpButton = new JButton("Sign Up");
    JButton resetButton = new JButton("Reset");
    SpringLayout layout = new SpringLayout();
    JPanel panel = new JPanel(layout);
    RegisterHandler registerHandler;

    public registerView() {
        super("Create New Account");
        registerHandler = new RegisterHandler(this);
        Container container = this.getContentPane();

        addComponent();
        container.add(panel);
        signUpButton.addActionListener(registerHandler);
        resetButton.addActionListener(registerHandler);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // set the windown location to be the center of our screen
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setBounds(screenWidth/3,screenHeight/3,screenWidth/3,screenHeight/3);
        this.setResizable(true);
        this.setVisible(true);

    }

    private void addComponent() {
        int titleoffset = Spring.width(title).getValue() / 2;
        layout.putConstraint(SpringLayout.WEST,title,-titleoffset,SpringLayout.HORIZONTAL_CENTER,panel);
        SpringLayout.Constraints usrConstraints = layout.getConstraints(userNameLabel);

        // set size for textfields
        userNameTxt.setPreferredSize(new Dimension(200,30));
        passWordTxt.setPreferredSize(new Dimension(200,30));
        confirmTxt.setPreferredSize(new Dimension(200,30));

        //set location for username label
        Spring usrWidth = Spring.width(userNameLabel);
        Spring usrTxtWidth = Spring.width(userNameTxt);
        Spring childWidth = Spring.sum(Spring.sum(usrWidth,usrTxtWidth),Spring.constant(20));
        int offsetUsr = childWidth.getValue()/2;
        layout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetUsr,SpringLayout.HORIZONTAL_CENTER,panel);
        usrConstraints.setY(Spring.constant(60));

        layout.putConstraint(SpringLayout.WEST,userNameTxt,20,SpringLayout.EAST,userNameLabel);
        layout.putConstraint(SpringLayout.NORTH,userNameTxt,-5,SpringLayout.NORTH,userNameLabel);
        layout.putConstraint(SpringLayout.WEST,passwordLabel,0,SpringLayout.WEST,userNameLabel);
        layout.putConstraint(SpringLayout.NORTH,passwordLabel,33,SpringLayout.SOUTH,userNameLabel);
        layout.putConstraint(SpringLayout.WEST,passWordTxt,0,SpringLayout.WEST,userNameTxt);
        layout.putConstraint(SpringLayout.NORTH,passWordTxt,20,SpringLayout.SOUTH,userNameTxt);

        layout.putConstraint(SpringLayout.EAST,confirmLabel,0,SpringLayout.EAST,passwordLabel);
        layout.putConstraint(SpringLayout.NORTH,confirmLabel,33,SpringLayout.SOUTH,passwordLabel);
        layout.putConstraint(SpringLayout.WEST,confirmTxt,0,SpringLayout.WEST,passWordTxt);
        layout.putConstraint(SpringLayout.NORTH,confirmTxt,20,SpringLayout.SOUTH,passWordTxt);

        Spring signWidth = Spring.width(signUpButton);
        Spring resetWidth = Spring.width(resetButton);
        Spring bottomWidth = Spring.sum(Spring.sum(signWidth,resetWidth), Spring.constant(20));
        int botOffset = bottomWidth.getValue()/2;

        layout.putConstraint(SpringLayout.WEST,signUpButton,-botOffset,SpringLayout.HORIZONTAL_CENTER,panel);
        SpringLayout.Constraints signConstraints = layout.getConstraints(signUpButton);
        signConstraints.setY(Spring.constant(210));
        layout.putConstraint(SpringLayout.WEST,resetButton,20,SpringLayout.EAST,signUpButton);
        layout.putConstraint(SpringLayout.NORTH,resetButton,0,SpringLayout.NORTH,signUpButton);

        panel.add(title);
        panel.add(userNameLabel);
        panel.add(userNameTxt);
        panel.add(passwordLabel);
        panel.add(passWordTxt);
        panel.add(confirmLabel);
        panel.add(confirmTxt);
        panel.add(signUpButton);
        panel.add(resetButton);
    }

    public JTextField getUserNameTxt() {
        return userNameTxt;
    }

    public JTextField getPassWordTxt() {
        return passWordTxt;
    }

    public JTextField getConfirmTxt() {
        return confirmTxt;
    }

    public void setUserNameTxt(JTextField userNameTxt) {
        this.userNameTxt = userNameTxt;
    }

    public void setPassWordTxt(JTextField passWordTxt) {
        this.passWordTxt = passWordTxt;
    }

    public void setConfirmTxt(JTextField confirmTxt) {
        this.confirmTxt = confirmTxt;
    }

    public static void main(String[] args) {
        new registerView();
    }

}

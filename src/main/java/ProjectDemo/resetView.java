package ProjectDemo;

import ProjectDemo.handler.ResetHandler;

import javax.swing.*;
import java.awt.*;

public class resetView extends JFrame {
    JLabel userNameLabel = new JLabel("username:");
    JLabel passwordLabel = new JLabel("password:");
    JLabel newLabel = new JLabel("new password:");
    JTextField userNameTxt = new JTextField();
    JTextField passWordTxt = new JTextField();
    JTextField newTxt = new JTextField();
    JButton confirmButton = new JButton("Confirm");
    JButton resetButton = new JButton("Reset");
    SpringLayout layout = new SpringLayout();
    JPanel panel = new JPanel(layout);
    ResetHandler resetHandler;

    public resetView(){
        super("Change Your Password");
        resetHandler = new ResetHandler(this);
        Container container = this.getContentPane();
        addComponent(container);
        confirmButton.addActionListener(resetHandler);
        resetButton.addActionListener(resetHandler);
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

    private void addComponent(Container container) {
        SpringLayout.Constraints usrConstraints = layout.getConstraints(userNameLabel);

        // set size for textfields
        userNameTxt.setPreferredSize(new Dimension(200,30));
        passWordTxt.setPreferredSize(new Dimension(200,30));
        newTxt.setPreferredSize(new Dimension(200,30));
        Spring usrWidth = Spring.width(userNameLabel);
        Spring usrTxtWidth = Spring.width(userNameTxt);
        Spring childWidth = Spring.sum(Spring.sum(usrWidth,usrTxtWidth),Spring.constant(20));
        int offsetUsr = childWidth.getValue()/2;
        layout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetUsr,SpringLayout.HORIZONTAL_CENTER,panel);
        usrConstraints.setY(Spring.constant(70));
        layout.putConstraint(SpringLayout.WEST,userNameTxt,20,SpringLayout.EAST,userNameLabel);
        layout.putConstraint(SpringLayout.NORTH,userNameTxt,-5,SpringLayout.NORTH,userNameLabel);
        layout.putConstraint(SpringLayout.WEST,passwordLabel,0,SpringLayout.WEST,userNameLabel);
        layout.putConstraint(SpringLayout.NORTH,passwordLabel,33,SpringLayout.SOUTH,userNameLabel);
        layout.putConstraint(SpringLayout.WEST,passWordTxt,0,SpringLayout.WEST,userNameTxt);
        layout.putConstraint(SpringLayout.NORTH,passWordTxt,20,SpringLayout.SOUTH,userNameTxt);

        layout.putConstraint(SpringLayout.EAST,newLabel,0,SpringLayout.EAST,passwordLabel);
        layout.putConstraint(SpringLayout.NORTH,newLabel,33,SpringLayout.SOUTH,passwordLabel);
        layout.putConstraint(SpringLayout.WEST,newTxt,0,SpringLayout.WEST,passWordTxt);
        layout.putConstraint(SpringLayout.NORTH,newTxt,20,SpringLayout.SOUTH,passWordTxt);

        Spring signWidth = Spring.width(confirmButton);
        Spring resetWidth = Spring.width(resetButton);
        Spring bottomWidth = Spring.sum(Spring.sum(signWidth,resetWidth), Spring.constant(20));
        int botOffset = bottomWidth.getValue()/2;
        layout.putConstraint(SpringLayout.WEST,confirmButton,-botOffset,SpringLayout.HORIZONTAL_CENTER,panel);
        SpringLayout.Constraints signConstraints = layout.getConstraints(confirmButton);
        signConstraints.setY(Spring.constant(230));
        layout.putConstraint(SpringLayout.WEST,resetButton,20,SpringLayout.EAST,confirmButton);
        layout.putConstraint(SpringLayout.NORTH,resetButton,0,SpringLayout.NORTH,confirmButton);
        panel.add(userNameLabel);
        panel.add(userNameTxt);
        panel.add(passwordLabel);
        panel.add(passWordTxt);
        panel.add(newLabel);
        panel.add(newTxt);
        panel.add(confirmButton);
        panel.add(resetButton);
        container.add(panel);
    }

    public JTextField getUserNameTxt() {
        return userNameTxt;
    }

    public JTextField getPassWordTxt() {
        return passWordTxt;
    }

    public JTextField getNewTxt() {
        return newTxt;
    }

    public static void main(String[] args) {
        new resetView();
    }
}

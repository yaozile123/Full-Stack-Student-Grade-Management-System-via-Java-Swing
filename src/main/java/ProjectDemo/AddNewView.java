package ProjectDemo;

import javax.swing.*;
import java.awt.*;

public class AddNewView extends JDialog {
//    SpringLayout layout = new SpringLayout();
    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel studentIdLabel = new JLabel("Student Id");
    JLabel studentNameLabel = new JLabel("Student Name");
    JLabel assign1Label = new JLabel("Assignment 1");
    JLabel quiz1Label = new JLabel("Quiz 1");
    JLabel mid1Label = new JLabel("Midterm 1");
    JTextField studentIdTxt = new JTextField();
    JTextField studentNameTxt = new JTextField();
    JTextField assign1Txt = new JTextField();
    JTextField quiz1Txt = new JTextField();
    JTextField mid1Txt = new JTextField();
    JButton addButton = new JButton("Add");
    public AddNewView(MainView mainView) {
        super(mainView,"Add New Student",true);
        studentIdLabel.setPreferredSize(new Dimension(100,30));
        studentIdTxt.setPreferredSize(new Dimension(200,30));
        studentNameLabel.setPreferredSize(new Dimension(100,30));
        studentNameTxt.setPreferredSize(new Dimension(200,30));
        assign1Label.setPreferredSize(new Dimension(100,30));
        assign1Txt.setPreferredSize(new Dimension(200,30));
        quiz1Label.setPreferredSize(new Dimension(100,30));
        quiz1Txt.setPreferredSize(new Dimension(200,30));
        mid1Label.setPreferredSize(new Dimension(100,30));
        mid1Txt.setPreferredSize(new Dimension(200,30));

        jPanel.add(studentIdLabel);
        jPanel.add(studentIdTxt);
        jPanel.add(studentNameLabel);
        jPanel.add(studentNameTxt);
        jPanel.add(assign1Label);
        jPanel.add(assign1Txt);
        jPanel.add(quiz1Label);
        jPanel.add(quiz1Txt);
        jPanel.add(mid1Label);
        jPanel.add(mid1Txt);
        jPanel.add(addButton);
        this.getContentPane().add(jPanel);
//        setSize(350,400);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int y = (screenHeight - 400) / 2 - 20;
        int x = (screenWidth - 350) / 2;
        this.setBounds(x,y,350,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}

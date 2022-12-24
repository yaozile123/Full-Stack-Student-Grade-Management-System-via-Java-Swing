package ProjectDemo.handler;

import ProjectDemo.AddNewView;
import ProjectDemo.LoginView;
import ProjectDemo.MainView;
import ProjectDemo.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHandler implements ActionListener {
    private MainView mainview;
    public MainHandler(MainView mainview) {
        this.mainview = mainview;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("Add New Student")){
            new AddNewView(mainview);
        } else if (text.equals("Search")) {
            mainview.setCurrentPage(1);
            mainview.reload();
        } else if (text.equals("Update")) {
            new UpdateView(mainview);
        } else if (text.equals("Delete")) {
            mainview.reload();
        } else if (text.equals("Prev Page")){
            mainview.setCurrentPage(mainview.getCurrentPage()-1);
            mainview.reload();
        } else if (text.equals("Next Page")){
            mainview.setCurrentPage(mainview.getCurrentPage()+1);
            mainview.reload();
        }

    }
}

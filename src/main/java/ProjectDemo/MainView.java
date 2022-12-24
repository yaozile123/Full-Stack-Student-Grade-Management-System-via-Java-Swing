package ProjectDemo;

import ProjectDemo.Service.StudentRequest;
import ProjectDemo.Service.StudentService;
import ProjectDemo.handler.MainHandler;
import ProjectDemo.reponse.TableDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Vector;

public class MainView extends JFrame {
    // northPanel use to store buttons like "search" "delete" "add new student"
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton = new JButton("Add New Student");
    JButton searchButton = new JButton("Search");
    JTextField searchTxt = new JTextField(15);
    JButton deleteButton = new JButton("Delete");
    JButton updateButton = new JButton("Update");
    // centralPanel use to store our dataframe

    // southPanel use to store other buttons like "Next Page", "Prev Page"
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton prevButton = new JButton("Prev Page");
    JButton nextButton = new JButton("Next Page");
    private int currentPage = 1;
    private int pageSize = 10;//rows of data in each page
    MainViewTableModel table;
    MainHandler mainHandler;
    MainViewTable mainTable;


    Vector<Vector<Object>> data = new Vector<>();
    public MainView() {
        super("Student Grade Management System");
        mainHandler = new MainHandler(this);
        mainTable = new MainViewTable();
        Container container = this.getContentPane();
        layoutNorth(container);
        layoutCentral(container);
        layoutSouth(container);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setVisible(true);
    }

    public void layoutNorth(Container container){
        // use for assemble north part of  mainview
        addButton.addActionListener(mainHandler);
        deleteButton.addActionListener(mainHandler);
        updateButton.addActionListener(mainHandler);
        searchButton.addActionListener(mainHandler);
        northPanel.add(addButton);
        northPanel.add(deleteButton);
        northPanel.add(updateButton);
        northPanel.add(searchTxt);
        northPanel.add(searchButton);
        container.add(northPanel,BorderLayout.NORTH);
    }

    public void layoutCentral(Container container){
        // use for assemble central part of mainview
        StudentRequest request = new StudentRequest();
        request.setPageSize(pageSize);
        request.setCurrentPage(currentPage);
        StudentService service = new StudentService();
        TableDTO tableDTO = service.retrieveStudent(request);
        data = tableDTO.getData();
        int count = tableDTO.getTotalCount();
        table = MainViewTableModel.assemble(data);
        mainTable.setModel(table);
        ((DefaultTableCellRenderer)mainTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        mainTable.renderRule();
        showPrevNext(count);
        JScrollPane scrollPane = new JScrollPane(mainTable);
        container.add(scrollPane,BorderLayout.CENTER);
    }

    public void layoutSouth(Container container){
        // use for assemble south part of mainview
        prevButton.addActionListener(mainHandler);
        nextButton.addActionListener(mainHandler);
        southPanel.add(prevButton);
        southPanel.add(nextButton);
        container.add(southPanel,BorderLayout.SOUTH);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void reload(){
        StudentRequest request = new StudentRequest();
        request.setPageSize(pageSize);
        request.setCurrentPage(currentPage);
        request.setSearchKey(searchTxt.getText().trim());
        StudentService service = new StudentService();
        TableDTO tableDTO = service.retrieveStudent(request);
        data = tableDTO.getData();
        MainViewTableModel.update(data);
        mainTable.renderRule();
        showPrevNext(tableDTO.getTotalCount());
    }

    private void showPrevNext(int cnt){
        if (currentPage == 1){
            prevButton.setEnabled(false);
        }
        else{
            prevButton.setEnabled(true);
        }
        int totalPage = 0;
        if (cnt % pageSize == 0){
            totalPage = cnt / pageSize;
        }
        else{
            totalPage = cnt / pageSize + 1;
        }
        if (currentPage == totalPage){
            nextButton.setEnabled(false);
        }else {
            nextButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new MainView();
    }

}

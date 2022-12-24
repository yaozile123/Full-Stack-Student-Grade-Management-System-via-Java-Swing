package ProjectDemo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {
    public MainViewTable() {
        JTableHeader header = getTableHeader();
        header.setFont(new Font(null,Font.BOLD,16));
        setFont(new Font(null,Font.PLAIN,14));
        setGridColor(Color.BLACK);
        setRowHeight(30);
    }


    public void renderRule(){
        Vector<String> columns = MainViewTableModel.getColumn();
        MainViewCellRender render = new MainViewCellRender();
        render.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i<columns.size();i++){
            TableColumn currentColumn = getColumn(columns.get(i));
            currentColumn.setCellRenderer(render);
            currentColumn.setResizable(false);
            if (i == 0){
                currentColumn.setPreferredWidth(50);
                currentColumn.setMaxWidth(50);
            }
        }
    }
}

class MainViewTableModel extends DefaultTableModel {
    static Vector<String> column = new Vector<>();
    static {
    column.addElement("id");
    column.addElement("Student Id");
    column.addElement("Student Name");
    column.addElement("Assignment 1");
    column.addElement("Quiz 1");
    column.addElement("Midterm 1");
    column.addElement("Final Grade");
    }
    private MainViewTableModel(){
        super(null,column);
    }

    public static MainViewTableModel model = new MainViewTableModel();
    public static MainViewTableModel assemble(Vector<Vector<Object>> data){
        model.setDataVector(data,column);
        return model;
    }

    public static void update(Vector<Vector<Object>> data){
        model.setDataVector(data,column);
    }
    public static Vector<String> getColumn(){
        return column;
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}

class MainViewCellRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row % 2 == 0){
            setBackground(Color.lightGray);
        }else{
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

class MainViewHeaderRender extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(JLabel.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}


package ProjectDemo.Service;

import ProjectDemo.DataBase;
import ProjectDemo.reponse.TableDTO;

import javax.swing.text.TabableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentService implements StudentInterface{
    @Override
    public TableDTO retrieveStudent(StudentRequest studentRequest) {
        // set the sql query
        StringBuilder sql = new StringBuilder();
        sql.append("select * from student_info ");
        if (studentRequest.getSearchKey() != null && !"".equals(studentRequest.getSearchKey().trim())){
            sql.append(" where Student_Name like '%"+studentRequest.getSearchKey().trim()+"%'");
        }
        sql.append("order by id limit ").append(studentRequest.getStart()).append(",").append(studentRequest.getPageSize());

        //get connection from mysql and execute the sql query
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TableDTO returnDTO = new TableDTO();//data we need to return
        try {
            connection = DataBase.getConnect();
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = preparedStatement.executeQuery();
            Vector<Vector<Object>> data = new Vector<>();
            int count = 0;
            while (resultSet.next()){
                Vector<Object> currentRecord = new Vector<>();
                int id = resultSet.getInt(1);
                int studentId = resultSet.getInt("Student_Id");
                String name = resultSet.getString("Student_Name");
                int assignment1 = resultSet.getInt("Assignment_1");
                int quiz1 = resultSet.getInt("Quiz_1");
                int midterm1 = resultSet.getInt("Midterm_1");
                int final_grade = (assignment1+quiz1+midterm1)/3;
                currentRecord.addElement(id);
                currentRecord.addElement(studentId);
                currentRecord.addElement(name);
                currentRecord.addElement(assignment1);
                currentRecord.addElement(quiz1);
                currentRecord.addElement(midterm1);
                currentRecord.addElement(final_grade);
                data.addElement(currentRecord);
            }

            // get the total count of the data
            sql.setLength(0);
            sql.append("select * from student_info ");
            if (studentRequest.getSearchKey() != null && !"".equals(studentRequest.getSearchKey().trim())){
                sql.append(" where Student_Name like '%"+studentRequest.getSearchKey().trim()+"%'");
            }
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count +=1;
            }
            returnDTO.setData(data);
            returnDTO.setTotalCount(count);
            return returnDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBase.closeConnect(connection);
            DataBase.closeStatement(preparedStatement);
            DataBase.closeResult(resultSet);
        }
    }
}

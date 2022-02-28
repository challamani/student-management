package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.logging.Logger;

public class StudentService {

    @FXML
    private TextField studentIdField;

    @FXML
    private Label showDetails;

    @FXML
    private Label recordStatusLabel;

    @FXML
    private TextField studentId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField fatherName;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField dob;

    @FXML
    private TextField group;

    private final static Logger logger = Logger.getLogger(StudentService.class.getName());

    public void viewRecord(ActionEvent event) throws Exception {
        String studentId = studentIdField.getText();
        String selectQuery = "select * from student where student_id = ? ";
        try {
            Connection connection = DatabaseService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, studentId);

            ResultSet rs = preparedStatement.executeQuery();
            StringBuffer stringBuffer = new StringBuffer();

            if (rs.next()) {
                stringBuffer.append("Student# :" + rs.getString("student_id") +"\n");
                stringBuffer.append(rs.getString("first_name") + " " + rs.getString("last_name") + "\n");
                stringBuffer.append("S/O " + rs.getString("father_name") + "\n");
                stringBuffer.append(rs.getString("dob") + "\n");
                stringBuffer.append(rs.getString("phone_no") + "\n");
                stringBuffer.append(rs.getString("address") + "\n");
                stringBuffer.append(rs.getString("group_name") + "\n");
                stringBuffer.append("System Entry :" +rs.getString("created_on") + "\n");
                stringBuffer.append("Due fee : 0.00000");
            } else {
                stringBuffer.append("No record found with given student# " + studentId);
            }
            showDetails.setText(stringBuffer.toString());
            connection.close();
        } catch (Exception ex) {
            logger.warning("failed at fetching student information {}" + ex.getMessage());
        }
    }

    public void ClearRecord(ActionEvent event) throws Exception {
        showDetails.setText("");
    }

    public void addRecord(ActionEvent event) {

        String studentIdStr = studentId.getText();
        String firstNameStr = firstName.getText();
        String lastNameStr = lastName.getText();
        String emailStr = email.getText();
        String dobStr = dob.getText();
        String fatherNameStr = fatherName.getText();
        String addressStr = address.getText();
        String phoneNoStr = phoneNo.getText();
        String groupStr = group.getText();

        String insertStatement = "insert into student " +
                " (student_id,first_name,last_name,email,father_name,dob,phone_no,group_name,address,status) " +
                " values " +
                " (?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection connection = DatabaseService.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(insertStatement);
            statement.setString(1, studentIdStr);
            statement.setString(2, firstNameStr);
            statement.setString(3, lastNameStr);
            statement.setString(4, emailStr);

            statement.setString(5, fatherNameStr);
            statement.setString(6, dobStr);
            statement.setString(7, phoneNoStr);
            statement.setString(8, groupStr);
            statement.setString(9, addressStr);
            statement.setString(10, "Active");
            int recordId = statement.executeUpdate();
            connection.close();
            recordStatusLabel.setText("Record added successfully, Student# :" + studentId + ", record# :" + recordId);
        } catch (Exception ex) {
            recordStatusLabel.setText("System Mal-function !" + ex.getMessage());
        }
    }

    public void updateStudent(ActionEvent event) throws Exception {

    }
}

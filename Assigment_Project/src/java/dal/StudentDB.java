/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IBaseModel;
import model.Student;

/**
 *
 * @author FPTSHOP
 */
public class StudentDB extends DBcontext<Student>{
    public ArrayList<Student> getStudentByGroupId(String group_id){
        ArrayList<Student> students = new ArrayList<>();
        String sql ="select * from Student where group_id =?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, group_id);;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Student student = new Student();
                student.setStudent_id(rs.getInt("student_id"));
                student.setStudent_name(rs.getString("student_name"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
}

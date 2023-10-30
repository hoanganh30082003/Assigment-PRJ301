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
import model.Subject;

/**
 *
 * @author FPTSHOP
 */
public class SubjectDB extends DBcontext<Subject> {

    public ArrayList<Subject> getSubject(int instructor_id) {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "select ss.subject_id,ss.group_id, su.subject_name\n"
                + "from session ss \n"
                + "inner join Subject su on ss.subject_id = su.subject_id\n"
                + "where ss.instructor_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, instructor_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return subjects; 
    }
}

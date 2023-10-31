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
        String sql = "SELECT i.Subject_id,s.subject_name\n"
                + "  FROM [dbo].[Instructor Subject] i\n"
                + "  inner join Subject s on i.Subject_id = s.subject_id\n"
                + "  where instructor_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, instructor_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getString("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                subjects.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return subjects;
    }
}

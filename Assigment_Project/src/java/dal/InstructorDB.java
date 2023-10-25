/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Instructor;

/**
 *
 * @author FPTSHOP
 */
public class InstructorDB extends DBcontext<Instructor> {
    public Instructor getByUsernamePassword(String username, String password) {
        String sql = "SELECT i.instructor_id, instructor_name\n"
                + "  FROM [dbo].Instructor i\n"
                + "  inner join Account a on a.instructor_id = i.instructor_id\n"
                + "  where username = ? and password = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Instructor i = new Instructor();
                i.setInstructor_id(rs.getString("instructor_id"));
                i.setInstructor_name(rs.getString("instructor_name"));
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

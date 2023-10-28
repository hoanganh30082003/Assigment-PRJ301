/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Campus;
import model.Instructor;

/**
 *
 * @author FPTSHOP
 */
public class AccountDBContext extends DBcontext<Account> {

    public Account get(Account model) {
        try {
            String sql = "select a.username ,a.password ,a.instructor_id,i.instructor_name,a.campus_id, c.campus_name\n"
                    + "from Account a\n"
                    + "inner join Instructor i on a.instructor_id = i.instructor_id\n"
                    + "inner join Campus c on a.campus_id = c.campus_id\n"
                    + "where a.username = ? and a.password = ? and c.campus_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getUsername());
            stm.setString(2, model.getPassword());
            stm.setString(3, model.getCampus().getCampus_id());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                Campus c = new Campus();
                c.setCampus_id(rs.getString("campus_id"));
                c.setCampus_name(rs.getString("campus_name"));
                acc.setCampus(c);
                Instructor i = new Instructor();
                i.setInstructor_id(rs.getInt("instructor_id"));
                i.setInstructor_name(rs.getString("instructor_name"));
                acc.setInstructor(i);
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

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
import model.Account;

/**
 *
 * @author FPTSHOP
 */
public class AccountDBContext extends DBcontext<Account> {       
    public Account get(Account model) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Account]\n"
                    + "  where username = ? and password = ? and campus_id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getUsername());
            stm.setString(2, model.getPassword());
            stm.setString(3, model.getCampus().getCampus_id());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(model.getUsername());
                acc.setPassword(model.getPassword());
                acc.setCampus(model.getCampus());
                acc.setInstructor(model.getInstructor());
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

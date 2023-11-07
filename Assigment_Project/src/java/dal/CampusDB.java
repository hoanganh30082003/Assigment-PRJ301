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
import model.Campus;

/**
 *
 * @author FPTSHOP
 */
public class CampusDB extends DBcontext<Campus> {
    public Campus getById(int campus_id) {
        String sql = "select * from Campus \n"
                + "where campus_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, campus_id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Campus c = new Campus();
                c.setCampus_id(rs.getInt("campus_id"));
                c.setCampus_name(rs.getString("campus_name"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampusDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public ArrayList<Campus> getCampus() {
        ArrayList<Campus> campuses = new ArrayList<>();
        String sql = "select * from Campus";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Campus c = new Campus();
                c.setCampus_id(rs.getInt("campus_id"));
                c.setCampus_name(rs.getString("campus_name"));
                campuses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampusDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return campuses;
    }
}

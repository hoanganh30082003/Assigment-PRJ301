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
import model.Group;

/**
 *
 * @author FPTSHOP
 */
public class GroupDB extends DBcontext<Group> {

    public ArrayList<Group> getGroup(String subject_id, int instructor_id) {
        ArrayList<Group> list = new ArrayList<>();
        String sql = "select gr.group_id , gr.subject_id\n"
                + "from Subject s \n"
                + "inner join [Group Subject] gr on s.subject_id = gr.subject_id\n"
                + "inner join [Group] g on gr.group_id = g.group_id\n"
                + "where s.subject_id = ? and g.instructor_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subject_id);
            stm.setInt(2, instructor_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setGroup_id(rs.getString("group_id"));
                g.setSubject_id(rs.getString("subject_id"));
                list.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
}

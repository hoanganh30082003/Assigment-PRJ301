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
import model.Session;
import model.Status;
import model.Student;

/**
 *
 * @author FPTSHOP
 */
public class StatusDB extends DBcontext<Status> {

    public ArrayList<Status> getStatusById(int id) {
        ArrayList<Status> list = new ArrayList<>();
        String sql = "select s.student_id,s.student_name,s.group_id\n"
                + ",isnull(st.comment,'')as comment\n"
                + ",isnull(st.status,0) as status\n"
                + ",isnull(ss.submit,GETDATE())as submit\n"
                + ",isnull(ss.isAtt,0) as isAtt\n"
                + "from Session ss \n"
                + "inner join Status st on ss.session_id = st.session_id\n"
                + "inner join Student s on st.student_id = s.student_id\n"
                + "where ss.session_id =?";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Status st = new Status();
                st.setComment(rs.getString("comment"));
                st.setStatus(rs.getBoolean("status"));
                
                Session s = new Session();
                s.setIsAtt(rs.getBoolean("isAtt"));
                st.setSession(s);
                
                Student sd = new Student();
                sd.setStudent_id(rs.getInt("student_id"));
                sd.setStudent_name(rs.getString("student_name"));
                st.setStudent(sd);
                
                list.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return list;
    }
}

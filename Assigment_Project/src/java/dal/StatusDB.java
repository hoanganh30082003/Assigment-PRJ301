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
        String sql = "select se.session_id, se.isAtt,st.student_id,st.student_name,s.comment,s.status\n"
                + "from Session se \n"
                + "inner join [Group] gr on se.group_id = gr.group_id\n"
                + "inner join Student st on st.group_id = gr.group_id\n"
                + "left join Status s on se.session_id = s.session_id and st.student_id = s.student_id\n"
                + "where se.session_id = ?";
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
                s.setSession_id(rs.getInt("session_id"));
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

    public ArrayList<Status> getStatus(String subject_id, String group_id) {
        ArrayList<Status> statuses = new ArrayList<>();
        String sql = "select sn.session_id,student_id,status\n"
                + "from Session sn\n"
                + "inner join Status ss on sn.session_id = ss.session_id \n"
                + "where subject_id = ? and group_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subject_id);
            stm.setString(2, group_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Status status = new Status();
                Session session = new Session();
                session.setSession_id(rs.getInt("session_id"));
                status.setSession(session);
                status.setStatus(rs.getBoolean("status"));
                
                Student student = new Student();
                student.setStudent_id(rs.getInt("student_id"));
                status.setStudent(student);
                statuses.add(status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statuses;
    }
}

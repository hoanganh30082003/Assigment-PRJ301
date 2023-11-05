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
import model.Session;
import model.Status;
import model.Student;

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

    public ArrayList<Group> getStudentStatus(String sid, String gid) {
        ArrayList<Group> list = new ArrayList<>();
        String sql = "select g.group_id, gs.subject_id,st.student_id,st.student_name,status,comment,session_id\n"
                + "from [Group] g \n"
                + "inner join [Group Subject] gs on g.group_id = gs.group_id\n"
                + "inner join Student st on g.group_id = st.group_id\n"
                + "inner join Status ss on st.student_id = ss.student_id\n"
                + "where gs.subject_id = ? and g.group_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setString(2, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Group gr = new Group();
                gr.setGroup_id(rs.getString("group_id"));
                gr.setSubject_id(rs.getString("subject_id"));
                
                Student s = new Student();
                s.setStudent_id(rs.getInt("student_id"));
                s.setStudent_name(rs.getString("student_name"));
                Session session = new Session();
                session.setSession_id(rs.getInt("session_id"));
                Status status = new Status();
                status.setStudent(s);
                status.setStatus(rs.getBoolean("status"));
                status.setComment(rs.getString("comment"));
                status.setSession(session);
                gr.setStatus(status);
                list.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}

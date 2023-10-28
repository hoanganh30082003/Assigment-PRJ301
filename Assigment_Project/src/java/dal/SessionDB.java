/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.Slot;
import model.Subject;

/**
 *
 * @author FPTSHOP
 */
public class SessionDB extends DBcontext<Session> {

    public List<Session> getLecture(int instructor_id, Date from, Date to) {
        List<Session> list = new ArrayList<>();
        String sql = "select s.session_id,s.subject_id,s.room_id,s.group_id,sl.slot_id,sl.start_time,sl.end_time,su.subject_name,s.date \n"
                + "from Session s \n"
                + "inner join [Group] g on s.group_id = g.group_id\n"
                + "inner join Slot sl on s.slot_id = sl.slot_id\n"
                + "inner join Subject su on su.subject_id = s.subject_id\n"
                + "where s.instructor_id = ? and s.date between ? and ?";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, instructor_id);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Session s = new  Session();
                s.setSession_id(rs.getInt("session_id"));
                s.setRoom_id(rs.getString("room_id"));
                
                s.setDate(rs.getDate("date"));
                s.setGroup_id(rs.getString("group_id"));
                
                Slot sl = new Slot();
                sl.setSlot_id(rs.getInt("slot_id"));
                sl.setStart_time(rs.getTime("start_time"));
                sl.setEnd_time(rs.getTime("end_time"));
                s.setSlot(sl);
                
                Subject su = new Subject();
                su.setSubject_id(rs.getInt("subject_id"));
                su.setSubject_name(rs.getString("subject_name"));
                s.setSubject(su);
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return list;
    }
}

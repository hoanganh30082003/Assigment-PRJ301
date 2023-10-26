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
import model.Course;
import model.Group;
import model.Lecture;
import model.Room;
import model.Slot;
import model.Subject;

/**
 *
 * @author FPTSHOP
 */
public class TtOfInstructorDB extends DBcontext<Lecture> {

    public List<Lecture> getLecture(String instructor_id, Date from, Date to) {
        List<Lecture> list = new ArrayList<>();
        String sql = "SELECT l.subject_id,subject_name,"
                + "lecture_id,sesion_name,"
                + "room_id,l.slot_id,date,"
                + "i.instructor_id,instructor_name,"
                + "gr.group_id,"
                + "sl.starttime,sl.endtime\n"
                + "FROM Lecture l\n"
                + "join dbo.Subject s on l.subject_id = s.subject_id\n"
                + "join Instructor i on s.instructor_id = i.instructor_id\n"
                + "join [Group] gr on s.course_id = gr.course_id\n"
                + "join slot sl on sl.slot_id = l.slot_id\n" +
        "WHERE i.instructor_id = ? and l.date BETWEEN ? AND ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, instructor_id);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecture l = new Lecture();
                l.setLecture_id(rs.getString("lecture_id"));
                l.setSesion_name(rs.getString("sesion_name"));
                l.setDate(rs.getDate("date"));
                
                Slot sl = new Slot();
                sl.setSlot_id(rs.getString("slot_id"));
                sl.setStarttime(rs.getTime("starttime"));
                sl.setEndtime(rs.getTime("endtime"));
                l.setSlot(sl);
                
                Group gr = new Group(); 
                gr.setGroup_id("group_id");
                Subject s = new Subject();
                s.setSubject_id(rs.getString("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                Course c = new Course();
                c.setGroup(gr);
                s.setCourse(c);
                l.setSubject(s);
                
                Room r = new Room();
                r.setRoom_id("room_id");
                l.setRoom(r);
                
                list.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TtOfInstructorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

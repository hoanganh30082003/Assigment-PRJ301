/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.SessionDB;
import dal.StatusDB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Session;
import model.Status;
import model.Student;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "Attendance", urlPatterns = {"/attendance"})
public class Attendance extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ses_id = Integer.parseInt(request.getParameter("id"));
        StatusDB sdb = new StatusDB();
        ArrayList<Status> listStatus = sdb.getStatusById(ses_id);
        request.setAttribute("status", listStatus);

        request.getRequestDispatcher("Attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ses_id = Integer.parseInt(request.getParameter("sesid"));
        String[] stuid = request.getParameterValues("stuid");
        Session ses = new Session();
        ses.setSession_id(ses_id);

        for (String student_id : stuid) {
            Status st = new Status();
            st.setSession(ses);
            st.setComment(request.getParameter("comment" + student_id));
            st.setStatus(request.getParameter("status" + student_id).equals("present"));
            Student s = new Student();
            s.setStudent_id(Integer.parseInt(student_id));
            st.setStudent(s);
            ses.getStatus().add(st);
            System.out.println(st.getComment());
        }
        SessionDB sdb = new SessionDB();
        sdb.setAtt(ses);
        StatusDB stdb = new StatusDB();
        ArrayList<Status> listStatus = stdb.getStatusById(ses_id);
        request.setAttribute("status", listStatus);
        
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        
        String alert = "Save successfully at "+ formattedTime+ ".";
        request.setAttribute("alert", alert);
        request.getRequestDispatcher("Attendance.jsp").forward(request, response);

    }
}

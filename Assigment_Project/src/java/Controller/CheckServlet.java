/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.GroupDB;
import dal.SessionDB;
import dal.StatusDB;
import dal.StudentDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Group;
import model.Session;
import model.Status;
import model.Student;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "CheckServlet", urlPatterns = {"/check"})
public class CheckServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String gid = request.getParameter("gid");
        StudentDB StudentDB = new StudentDB();
        SessionDB sessionDB = new SessionDB();
        StatusDB statusDB = new StatusDB();
        ArrayList<Student> students = StudentDB.getStudentByGroupId(gid);
        ArrayList<Session> sessions = sessionDB.getSession(sid, gid);
        ArrayList<Status> statuses = statusDB.getStatus(sid, gid);

        Map<String, Integer> studentAbsencesMap = calculateStudentAbsences(statuses); 
        Map<String, Double>  studentAttendancePercentageMap = calculateStudentAttendancePercentage(statuses);
        
        request.setAttribute("studentAttendancePercentageMap", studentAttendancePercentageMap);
        request.setAttribute("studentAbsencesMap", studentAbsencesMap);
        request.setAttribute("statuses", statuses);
        request.setAttribute("sessions", sessions);
        request.setAttribute("students", students);
        request.getRequestDispatcher("Report.jsp").forward(request, response);
    }

    private Map<String, Integer> calculateStudentAbsences(ArrayList<Status> statuses) {
        Map<String, Integer> studentAbsencesMap = new HashMap<>();

        for (Status status : statuses) {
            String studentId = String.valueOf(status.getStudent().getStudent_id()); 
            if (!status.isStatus()) { 
                if (studentAbsencesMap.containsKey(studentId)) {
                    int currentAbsences = studentAbsencesMap.get(studentId);
                    studentAbsencesMap.put(studentId, currentAbsences + 1);
                } else {
                    studentAbsencesMap.put(studentId, 1);
                }
            }
        }

        return studentAbsencesMap;
    }
    private Map<String, Double> calculateStudentAttendancePercentage( ArrayList<Status> statuses) {
        Map<String, Double> studentAttendancePercentageMap = new HashMap<>();
        Map<String, Integer> studentTotalAbsencesMap = new HashMap<>();
        
        for (Status status : statuses) {
            String studentId = String.valueOf(status.getStudent().getStudent_id());
            if (!status.isStatus()) { 
                if (studentTotalAbsencesMap.containsKey(studentId)) {
                    int currentAbsences = studentTotalAbsencesMap.get(studentId);
                    studentTotalAbsencesMap.put(studentId, currentAbsences + 1);
                } else {
                    studentTotalAbsencesMap.put(studentId, 1);
                }
            }
        }
        // Tính phần trăm buổi nghỉ của từng học sinh
        for (Status status : statuses) {
            String studentId = String.valueOf(status.getStudent().getStudent_id());
            double totalAbsences = studentTotalAbsencesMap.get(studentId);
            double totalSessions = statuses.size();

            double attendancePercentage = ((totalSessions - totalAbsences) / totalSessions) * 100;
            studentAttendancePercentageMap.put(studentId, attendancePercentage);
        }

        return studentAttendancePercentageMap;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

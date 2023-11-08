/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.SlotDB;
import dal.SessionDB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Session;
import model.Slot;
import util.DateUtils;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "TtOfInstructor", urlPatterns = {"/schedule"})
public class TtOfInstructor extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account loggedAcc = (Account) session.getAttribute("acc");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        ArrayList<Date> dates = new ArrayList<>();

        if (from == null && to == null) {
            dates = (ArrayList<Date>) DateUtils.getDatesOfCurrentWeek();
        } else {
            try {
                dates = (ArrayList<Date>) DateUtils.getSQLDatesBetween(from, to);
            } catch (ParseException ex) {
                Logger.getLogger(TtOfInstructor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (dates.isEmpty()) {
            dates = (ArrayList<Date>) DateUtils.getDatesOfCurrentWeek();
        }
        Date fromDate = dates.get(0);
        Date toDate = dates.get(dates.size() - 1);
        SlotDB sl = new SlotDB();
        List<Slot> slots = sl.getSlot();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM");
        ArrayList<String> dateFormat = new ArrayList<>();
        for (Date date : dates) {
            String formattedDate = f.format(date);
            dateFormat.add(formattedDate);
        }
        int id = loggedAcc.getInstructor().getInstructor_id();
        SessionDB tdb = new SessionDB();
        List<Session> list = tdb.getSession(id, fromDate, toDate);

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTime = currentTime.format(formatter);
        
        request.setAttribute("currentDay", formattedTime);
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("dateFormat", dateFormat);
        request.setAttribute("from", fromDate);
        request.setAttribute("to", toDate);
        request.setAttribute("session", list);
        request.getRequestDispatcher("ttOfInstructor.jsp").forward(request, response);

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
        processRequest(request, response);
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

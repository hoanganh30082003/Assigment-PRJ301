/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.AccountDBContext;
import dal.CampusDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Campus;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        CampusDB cdb = new CampusDB();
        ArrayList<Campus> campuses = cdb.getCampus();
        HttpSession session = request.getSession();
        session.setAttribute("campuses", campuses);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        int campus_id = Integer.parseInt(request.getParameter("campus"));
        Account param = new Account();
        param.setUsername(user);
        param.setPassword(password);
        CampusDB cdb = new CampusDB();
        param.setCampus(cdb.getById(campus_id));
        AccountDBContext db = new AccountDBContext();
        Account loggedAcc = db.get(param);
        
        if (loggedAcc == null) {
                String error = "Invalid username or password!";
                request.setAttribute("error", error);
                request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (loggedAcc.getCampus().getCampus_id() != campus_id) {
                request.setAttribute("error", "invalid campus!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            HttpSession session = request.getSession();
            session.setAttribute("acc", loggedAcc);
            request.getRequestDispatcher("schedule").forward(request, response);

        }
    }
}

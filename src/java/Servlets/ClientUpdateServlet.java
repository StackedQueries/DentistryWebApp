/********************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      ClientUpdateServlet.java
 ********************************************************************/
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.Dentist;
import Business.Patient;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ClientUpdateServlet", urlPatterns = {"/ClientUpdateServlet"})
public class ClientUpdateServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String id = (String) session.getAttribute("id");

            if (id.substring(0, 1).equals("A")) { // updates Patient if ID is Patient ID
                String pass = request.getParameter("password");
                String fName = request.getParameter("firstName");
                String lName = request.getParameter("lastName");
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                String insco = request.getParameter("insCo");
                Patient pat = new Patient();
                pat.selectDB(id);
                pat.updateDB(id, pass, fName,lName, address, email, insco);
            }

            if (id.substring(0, 1).equals("D")) { //update Dentist if ID is DentistID
                                String pass = request.getParameter("password");
                String fName = request.getParameter("firstName");
                String lName = request.getParameter("lastName");
                String email = request.getParameter("email");
                 String office= request.getParameter("office");
                Dentist dent = new Dentist();
                dent.selectDB(id);
                dent.updateDB(id,pass, fName, lName,email,office);
            }
                RequestDispatcher rd = request.getRequestDispatcher("/WelcomeMenu.jsp"); //Sends to menu screen
                rd.forward(request, response);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClientUpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientUpdateServlet at " + request.getContextPath() + "</h1>");
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

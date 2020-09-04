/********************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      LoginServlet.java
 ********************************************************************/
package Servlets;

import Business.Dentist;
import Business.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            
            //pulls User and Pass from login screen
            String id;
            id = request.getParameter("UserName");
            String pw;
            pw = request.getParameter("password");

            //makes sure that there is a password
            if(pw.isEmpty()){
                RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp");
                rd.forward(request, response);
            }
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");

            HttpSession ses1;
            String pass = null;
            ses1 = request.getSession();
            ses1.setAttribute("id", id);
            
            if (id.substring(0, 1).equals("A")) { //finds out account type
                Patient p = new Patient();
                p.selectDB(id);
                pass = p.getPass();
                

            } else if (id.substring(0, 1).equals("D")) { //finds out account type
                Dentist d = new Dentist();
                d.selectDB(id);
                pass = d.getPass();
            }

            
            ses1.setAttribute("id", id);
            if (pw.equals(pass)) {

                out.println("<h1>Valid Login</h1>");
                RequestDispatcher rd = request.getRequestDispatcher("/WelcomeMenu.jsp"); //login successful
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp"); //login unsuccessful
                rd.forward(request, response);

            }

            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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

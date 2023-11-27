

package control;

import dao.DAO;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Tieu_Dat
 */
@WebServlet(name="GradeControl", urlPatterns={"/show_grade"})
public class GradeControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int id = a.getId();
        DAO dao = new DAO();
        List<String> listtermname = dao.getTermByStudentID(id);
        List<Term> listterm = new ArrayList<>();
        double tl_10 = 0, tl_4 = 0;
        int tl = 0;
        for(String s : listtermname) {
            List<CoursesRegistration> tmp = dao.getCourseRegistrationByStudentID(id, s);
            Term t = new Term(s, tmp);
            listterm.add(t);
        }
        Collections.sort(listterm);
        
        for(Term t : listterm) {
            tl_10 += t.getAvg_10() * t.getTotal_credit();
            tl_4 += t.getAvg_4() * t.getTotal_credit();
            tl += t.getTotal_credit();
            t.setTl_10(Math.round((tl_10 / tl)*100.0) / 100.0);
            t.setTl_4(Math.round((tl_4 / tl)*100.0) / 100.0);
            t.setTl_credit(tl);
        }
        
        request.setAttribute("listT", listterm);
        request.getRequestDispatcher("Grade.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

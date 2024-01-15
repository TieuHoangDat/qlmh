

package control;

import dao.DAO;
import entity.Account;
import entity.Course;
import entity.Group;
import entity.GroupRegistration;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import java.util.List;

/**
 *
 * @author Tieu_Dat
 */
@WebServlet(name="CourseControl", urlPatterns={"/course"})
public class CourseControl extends HttpServlet {
   
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
        String courseID = request.getParameter("id");
        
        DAO dao = new DAO();
        List<Group> listg = dao.getGroupByCourseID(courseID);
        List<Course> listc = dao.getCourseByStudentID(id);
        List<GroupRegistration> listgr = dao.getGroupRegistrationByStudentID(id);
        
        for(Group x : listg) {
            boolean ok = false;
            for(GroupRegistration y : listgr) {
                if(x.getGroup_id().equals(y.getGroup().getGroup_id())) {
                    ok = true;
                }
            }
            x.setRegister(ok);
        }
        
        request.setAttribute("registercourseactive", "active");    
        request.setAttribute("listGR", listgr);            
        request.setAttribute("listC", listc);       
        request.setAttribute("listG", listg);
        request.setAttribute("tag", courseID);
        request.getRequestDispatcher("GroupRegistration.jsp").forward(request, response);
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

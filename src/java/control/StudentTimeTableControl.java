

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

/**
 *
 * @author Tieu_Dat
 */
@WebServlet(name="StudentTimeTableControl", urlPatterns={"/studenttimetable"})
public class StudentTimeTableControl extends HttpServlet {
   
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
            Group b[][] = new Group[10][10];
            
            if(a.getIsTeacher()==0) {
                List<GroupRegistration> listgr = dao.getGroupRegistrationByStudentID(id);
                for(GroupRegistration o : listgr) {
                    int x = Integer.parseInt(o.getGroup().getTime().substring(11));
                    int y = Integer.parseInt(o.getGroup().getTime().substring(4, 5));                   
                    b[x][y] = o.getGroup();
                }
            } else {
                List<Group> listg = dao.getGroupByTeacherID(id);
                for(Group o : listg) {
                    int x = Integer.parseInt(o.getTime().substring(11));
                    int y = Integer.parseInt(o.getTime().substring(4, 5));                   
                    b[x][y] = o;
                }
            }
            
            request.setAttribute("b", b);
            request.getRequestDispatcher("StudentTimeTable.jsp").forward(request, response);
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

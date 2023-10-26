

package control;

import dao.DAO;
import entity.Account;
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
import java.util.List;

/**
 *
 * @author Tieu_Dat
 */
@WebServlet(name="AddGroupRegisterControl", urlPatterns={"/addGroupRegister"})
public class AddGroupRegisterControl extends HttpServlet {
   
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
        String grid = request.getParameter("grid");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int accid = a.getId();
        DAO dao = new DAO();
        List<GroupRegistration> listgr = dao.getGroupRegistrationByStudentID(accid);
        Group group = dao.getGroupByID(grid);
        
        // Kiểm tra để một môn không được đăng ký 2 nhóm
        boolean check_course = false;
        for(GroupRegistration x : listgr) {
            if(group.getCourse().getId().equals(x.getGroup().getCourse().getId())) {
                check_course = true;
            }
        }
        
        // Kiểm tra nhóm còn slot để đăng ký không
        boolean check_slot = false;
        if(group.getAvailable_slot()<=0) {
            check_slot = true;
        }
        
        // Kiểm tra xem có trừng thời khóa biểu không
        boolean check_time = false;
        for(GroupRegistration x : listgr) {
            if(group.getTime().equals(x.getGroup().getTime())) {
                check_time = true;
            }
        }
        
        
        if(check_course) {
            request.setAttribute("blockAlert", "block");
            request.setAttribute("mess", "Môn học đã được đăng ký");
            request.getRequestDispatcher("register").forward(request, response);
        }
        else if(check_slot) {
            request.setAttribute("blockAlert", "block");
            request.setAttribute("mess", "Nhóm này đã hết chỗ");
            request.getRequestDispatcher("register").forward(request, response);
        }
        else if(check_time) {
            request.setAttribute("blockAlert", "block");
            request.setAttribute("mess", "Trùng thời gian");
            request.getRequestDispatcher("register").forward(request, response);
        }
        else {
            dao.AddGroupRegister(accid, grid);
            response.sendRedirect("register");
        }
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

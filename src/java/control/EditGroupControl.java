/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Group;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "EditGroupControl", urlPatterns = {"/editgroup"})
public class EditGroupControl extends HttpServlet {

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
        String idEdit = request.getParameter("idEdit");
        String edit = request.getParameter("edit");
        if(edit==null){
            Group group = new DAO().getGroupByID(idEdit);
            request.setAttribute("group", group);
            request.setAttribute("blockEdit", "block");
            request.getRequestDispatcher("managergroup").forward(request, response);
        }else{
            String name_group_edit = (String)request.getParameter("name_group_edit");
            String course_id_edit = (String)request.getParameter("course_id_edit");
            String day_edit = (String)request.getParameter("day_edit");
            String time_edit = (String)request.getParameter("time_edit");
            String teacher_name_edit = (String)request.getParameter("teacher_name_edit");
            String teacher_id_edit = (String)request.getParameter("teacher_id_edit");
            String room_edit = (String)request.getParameter("room_edit");
            String quantity_student_edit = (String)request.getParameter("quantity_student_edit");
            new DAO().EditGroup(name_group_edit, course_id_edit, day_edit, time_edit, teacher_name_edit, teacher_id_edit, room_edit, quantity_student_edit);
//            new DAO().EditGroup("Nhóm 3", "INT1319", "2", "7", "Sơn", "12", "402-A2", "333333");
            response.sendRedirect("managergroup");
        }
        
//        response.sendRedirect("managergroup?blockEdit=block");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Course;
import entity.Group;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ManagerGroupContrrol", urlPatterns = {"/managergroup"})
public class ManagerGroupContrrol extends HttpServlet {

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
        DAO dao = new DAO();
        List<Group> list = dao.getAllGroup();
        String blockDelete = (String)request.getParameter("blockDelete");
        String blockEdit = (String)request.getParameter("blockEdit");
        String idDelete = (String)request.getParameter("idDelete");
        String idEdit = (String)request.getParameter("idEdit");
        String blockAddGrade = (String)request.getParameter("blockGrade");
        String idaddgrade = (String)request.getParameter("idaddgrade");
        Group group = dao.getGroupByID(idEdit);
        
        request.setAttribute("managergroupactive", "active");
        request.setAttribute("listG", list);
        request.setAttribute("idDelete", idDelete);
        request.setAttribute("idEdit", idEdit);
        request.setAttribute("blockDelete", blockDelete );
        request.setAttribute("blockEdit", blockEdit);
        request.setAttribute("group", group);
        request.setAttribute("blockAddGrade", blockAddGrade);
        request.setAttribute("idaddgrade", idaddgrade);
        request.getRequestDispatcher("ManagerGroup.jsp").forward(request, response);
        
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

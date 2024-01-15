/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.CoursesRegistration;
import entity.Group;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddGrade", urlPatterns = {"/addgrade"})
public class AddGrade extends HttpServlet {

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
        String check = request.getParameter("check");
        String upgrade = request.getParameter("upgrade");
        if(check==null){
            String idaddgrade = request.getParameter("idaddgrade");
            response.sendRedirect("managergroup?blockGrade=block&idaddgrade="+idaddgrade);
        }else{
            DAO dao = new DAO();
            List<Group> listG = dao.getAllGroup();
            String path = request.getParameter("filepath");
            String idGroup = request.getParameter("idaddgrade");
            List<CoursesRegistration> listCR =  new DAO().readExcel(path,idGroup);
            request.setAttribute("listCR", listCR);
            request.setAttribute("listG", listG);
            request.setAttribute("idGroup", idGroup);
            request.setAttribute("blockReviewGrade", "block");
//                response.sendRedirect("managergroup");

            request.getRequestDispatcher("ManagerGroup.jsp").forward(request, response);        }
//        if(upgrade!=null){
//            response.sendRedirect("home");
//        }
        
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
//        new DAO().readExcel("C:\\Users\\ASUS\\Desktop\\Book2.xlsx");
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

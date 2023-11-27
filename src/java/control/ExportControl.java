package control;

import dao.DAO;
import entity.Account;
import entity.CoursesRegistration;
import entity.Term;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(name="ExportControl", urlPatterns={"/exportExcel"})
public class ExportControl extends HttpServlet {

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
        
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Grade.xlsx");

        try (OutputStream out = response.getOutputStream()) {
            // Create a new Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            
            Row row0 = sheet.createRow(2);
            row0.createCell(0).setCellValue("Bảng điểm của sinh viên: " + a.getName());
            
            int rowIndex = 4;
            
            for(Term t : listterm) {
                Row row1 = sheet.createRow(rowIndex++);
                row1.createCell(0).setCellValue(t.getTerm());
                
                // Create a header row
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("Mã môn học");
                headerRow.createCell(1).setCellValue("Tên môn học");
                headerRow.createCell(2).setCellValue("Số tín chỉ");
                headerRow.createCell(3).setCellValue("Điểm TK(10)");
                headerRow.createCell(4).setCellValue("Điểm TK(4)");
                headerRow.createCell(5).setCellValue("Điểm TK(C)");

                // Create data rows

                for (CoursesRegistration x : t.getLi()) {
                    Row dataRow = sheet.createRow(rowIndex++);
                    dataRow.createCell(0).setCellValue(x.getCourse().getId());
                    dataRow.createCell(1).setCellValue(x.getCourse().getName());
                    dataRow.createCell(2).setCellValue(x.getCourse().getNum_credit());
                    dataRow.createCell(3).setCellValue(x.getGrade_10());
                    dataRow.createCell(4).setCellValue(x.getGrade_4());
                    dataRow.createCell(5).setCellValue(x.getGrade_alpha());
                }
                Row row2 = sheet.createRow(rowIndex++);
                row2.createCell(0).setCellValue("- Điểm trung bình học kỳ hệ 4: " + t.getAvg_4());
                row2.createCell(4).setCellValue("- Điểm trung bình tích lũy hệ 4: " + t.getTl_4());
                
                Row row3 = sheet.createRow(rowIndex++);
                row3.createCell(0).setCellValue("- Điểm trung bình học kỳ hệ 10: " + t.getAvg_10());
                row3.createCell(4).setCellValue("- Điểm trung bình tích lũy hệ 10: " + t.getTl_10());
                
                Row row4 = sheet.createRow(rowIndex++);
                row4.createCell(0).setCellValue("- Số tín chỉ đạt học kỳ: " + t.getTotal_credit());
                row4.createCell(4).setCellValue("- Số tín chỉ tích lũy: " + t.getTl_credit());
                
                rowIndex++;
            }

            // Write the workbook content to the response output stream
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Export to Excel Servlet";
    }
}

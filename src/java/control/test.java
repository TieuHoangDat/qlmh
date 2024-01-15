package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcelReaderServlet", urlPatterns = {"/excel-reader"})
public class test extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Lấy đường dẫn tệp Excel từ tham số yêu cầu
            String excelFilePath = request.getParameter("excelFilePath");

            if (excelFilePath != null && !excelFilePath.isEmpty()) {
                try {
                    FileInputStream inputStream = new FileInputStream(excelFilePath);
                    Workbook workbook = new XSSFWorkbook(inputStream);

                    // Lấy sheet đầu tiên (hoặc có thể lựa chọn sheet khác)
                    Sheet sheet = workbook.getSheetAt(0);

                    // Lặp qua từng hàng
                    for (Row row : sheet) {
                        // Lặp qua từng ô trong hàng
                        for (Cell cell : row) {
                            out.print(cell.toString() + "\t");
                        }
                        out.println("<br>");
                    }

                    // Đóng tệp Excel
                    workbook.close();
                    inputStream.close();
                } catch (IOException e) {
                    out.println("Error reading Excel file: " + e.getMessage());
                }
            } else {
                out.println("Please provide a valid Excel file path.");
            }
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
        return "Excel Reader Servlet";
    }
}
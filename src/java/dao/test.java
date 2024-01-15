package dao;

import entity.Grade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test {

    public List<Grade> readExcel(String filePath) {
        List<Grade> list=new ArrayList<>();
        try {
            // Đường dẫn đến tệp Excel
            String excelFilePath = filePath;

            // Tạo một FileInputStream để đọc tệp Excel
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            // Tạo một đối tượng Workbook từ FileInputStream
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            // Chọn một sheet (ví dụ: sheet đầu tiên)
            Sheet sheet = workbook.getSheetAt(0);

            // Lặp qua từng hàng của sheet
            int dem = -1;
            for (Row row : sheet) {
                String ma="",firstName="" , lastName="" ;
                double diem=-1;
                dem++;
                // Lặp qua từng ô của hàng
                if(dem < 3){
                    continue;
                }
                int cot = -1;
                for (Cell cell : row) {
                    cot++;
                    if(cot==1){
                        if (cell.getCellType() == CellType.NUMERIC) {
                        double numericValue = cell.getNumericCellValue();
                        // Kiểm tra xem giá trị có phải là số nguyên hay không
                        if (numericValue == Math.floor(numericValue) && !Double.isInfinite(numericValue)) {
                            // Giá trị là số nguyên, sử dụng String.format để in ra
                            ma = String.format(Locale.US, "%.0f ", numericValue);
                         System.out.print(String.format(Locale.US, "%.0f ", numericValue));
                        }
                        }
                    }
                    if(cot==2){
                        firstName = cell.toString();
                    }
                    if(cot==3){
                        lastName = cell.toString();
                    }
                    if(cot==10){
                        try{
                            diem = Double.parseDouble(cell.toString());
                        }catch(Exception e){
                            
                        }
                        
                    }
                    if(!ma.equals("") && diem>=0 ){
                        new DAO().editCourseRegister(ma, "INT1319", diem);
                          list.add(new Grade(ma,firstName+lastName,diem));
                    }
                    
                    
                }
            }

            // Đóng FileInputStream và Workbook sau khi hoàn thành
            inputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
//        new test().readExcel("C:\\Users\\ASUS\\Desktop\\Book2.xlsx");
          List<Grade> list = new test().readExcel("C:\\Users\\ASUS\\Desktop\\Book2.xlsx");
          for(Grade x : list){
              new DAO().editCourseRegister(x.getMa(), "INT1319", x.getDiem());
          }
    }
}

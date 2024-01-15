package dao;

import context.DBContext;
import entity.Account;
import entity.Course;
import entity.CoursesRegistration;
import entity.Grade;
import entity.Group;
import entity.GroupRegistration;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tieu_Dat
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<String> getTermByStudentID(int id) {
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT term\n" +
                    "FROM CourseRegistrations\n" +
                    "WHERE account_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setInt(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
        }

        return list;
    }
    
    public List<CoursesRegistration> getCourseRegistrationByStudentID(int id, String term) {
        List<CoursesRegistration> list = new ArrayList<>();
        String query = "SELECT C.*, CR.*\n" +
                        "FROM Courses C\n" +
                        "INNER JOIN CourseRegistrations CR ON C.course_id = CR.course_id\n" +
                        "WHERE CR.account_id = ?\n" +
                        "AND CR.term = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setInt(1, id);
            ps.setString(2, term);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new CoursesRegistration(null, 
                        new Course(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getFloat(10)));
            }
        } catch (Exception e) {
        }
        
        return list;
    }

    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();
        String query = "select * from Courses";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Course(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Group> getAllGroup() {
        List<Group> list = new ArrayList<>();
        String query = "SELECT * \n"
                + "FROM Groups\n";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
//            ps.setString(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Group(rs.getString(1),
                        rs.getString(2),
                        new Course(rs.getString(3)),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)));
            }
        } catch (Exception e) {
        }
        for (Group x : list) {
            x.setCourse(getCourse(x.getCourse().getId()));
        }
        return list;
    }
    
    public List<Course> getCourseByStudentID(int id) {
        List<Course> list = new ArrayList<>();
        String query = "SELECT C.course_id, C.course_name, C.num_credit, C.term\n"
                + "FROM Courses AS C\n"
                + "JOIN CourseRegistrations AS CR\n"
                + "    ON C.course_id = CR.course_id\n"
                + "WHERE CR.account_id = ? AND CR.term = N'Kỳ 1 năm học 2023-2024';";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setInt(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Course(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Group> getGroupByCourseID(String id) {
        List<Group> list = new ArrayList<>();
        String query = "SELECT * \n"
                + "FROM Groups\n"
                + "WHERE course_id = ?;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setString(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Group(rs.getString(1),
                        rs.getString(2),
                        new Course(rs.getString(3)),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)));
            }
        } catch (Exception e) {
        }
        for (Group x : list) {
            x.setCourse(getCourse(x.getCourse().getId()));
        }
        return list;
    }

    public Group getGroupByID(String id) { // lấy thêm cả Course của group
        String query = "SELECT Groups.*, Courses.*\n"
                + "FROM Groups\n"
                + "JOIN Courses ON Groups.course_id = Courses.course_id\n"
                + "WHERE Groups.group_id = ?;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setString(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                return new Group(rs.getString(1),
                        rs.getString(2),
                        new Course(rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13)),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Group> getGroupByTeacherID(int id) { // lấy thêm cả Course của group
        List<Group> list = new ArrayList<>();
        String query = "SELECT Groups.*, Courses.*\n"
                + "FROM Groups\n"
                + "JOIN Courses ON Groups.course_id = Courses.course_id\n"
                + "WHERE Groups.teacher_id  = ?;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setInt(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                Group a = new Group(rs.getString(1),
                        rs.getString(2),
                        new Course(rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13)),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9));
                list.add(a);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Group> getGroupByAccountID(int id) { // lấy tất cả các Group của các Course mà sv đăng ký
        List<Group> list = new ArrayList<>();
        String query = "SELECT G.*, C.*\n"
                + "FROM Groups G\n"
                + "JOIN Courses C ON G.course_id = C.course_id\n"
                + "JOIN CourseRegistrations CR ON C.course_id = CR.course_id\n"
                + "WHERE CR.account_id = ?;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setInt(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Group(rs.getString(1),
                        rs.getString(2),
                        new Course(rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13)),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<GroupRegistration> getGroupRegistrationByStudentID(int id) {
        List<GroupRegistration> list = new ArrayList<>();
        String query = "SELECT G.group_id, GR.[time]\n"
                + "FROM GroupRegistrations GR\n"
                + "INNER JOIN Groups G ON GR.group_id = G.group_id\n"
                + "WHERE GR.account_id = ?;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setInt(1, id);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new GroupRegistration(null,
                        new Group(rs.getString(1)),
                        rs.getTimestamp(2)));
            }
        } catch (Exception e) {
        }
        for (GroupRegistration x : list) {
            x.setGroup(getGroupByID(x.getGroup().getGroup_id()));
        }
        return list;
    }

    public Account login(String user, String pass) {
        String query = "select * from Accounts\n"
                + "where [username] = ?\n"
                + "and password = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void DeleteGroupRegister(int accid, String grid) {
        String query = "DELETE FROM GroupRegistrations\n"
                + "WHERE account_id = ? AND group_id = ?;"
                + "UPDATE Groups\n"
                + "SET available_slots = available_slots + 1\n"
                + "WHERE group_id = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accid);
            ps.setString(2, grid);
            ps.setString(3, grid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void AddGroupRegister(int accid, String grid) {
        String query = "INSERT INTO GroupRegistrations (account_id, group_id, [time])\n"
                + "VALUES (?, ?, GETDATE());"
                + "UPDATE Groups\n"
                + "SET available_slots = available_slots - 1\n"
                + "WHERE group_id = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accid);
            ps.setString(2, grid);
            ps.setString(3, grid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
//    duong them

    public void AddCourseInDatabase(String name, String id, String numberOfCredit, String semester) {
        String query = "insert into Courses values (?,? ,?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // xet dau hoi cham thu nhat la cid
            ps.setString(2, name); // xet dau hoi cham thu nhat la cid
            ps.setString(3, numberOfCredit); // xet dau hoi cham thu nhat la cid
            ps.setString(4, semester); // xet dau hoi cham thu nhat la cid
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void DeleteCourse(String id) {
        String query = "delete from Courses where course_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // xet dau hoi cham thu nhat la cid
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void EditCourse(String name, String id, int numCredit, int term) {
        String query = "update Courses set course_id = ? , course_name = ? , num_credit = ? , term = ?  where course_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // xet dau hoi cham thu nhat la cid
            ps.setString(2, name);
            ps.setInt(3, numCredit);
            ps.setInt(4, term);
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Course getCourse(String id) {
        String query = "SELECT * FROM Courses where course_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Course(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getCourseByNameOrId(String searchCourse) {
        List<Course> list = new ArrayList<>();
        String query = "select * from Courses where course_id like ? or course_name like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setString(1, "%"+searchCourse+"%");
            ps.setString(2, "%"+searchCourse+"%");
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Course(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }

        return list;

    }

    public void AddGroup(String name_group, String course_id, String day, String time,String teacher_name, String teacher_id, String room, String quantity_student){
        String query = "insert into Groups values (?,?,?,?,?,?,?,?,?)";
        //id
        String id = course_id+"_"+String.format("N%02d", Integer.parseInt(name_group.substring(5)));
//        String id = "1";
        // thoi gian
        String thoigian = "Thứ "+day +", kíp" + time;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // xet dau hoi cham thu nhat la cid
            ps.setString(2, name_group); // xet dau hoi cham thu nhat la cid
            ps.setString(3, course_id); // xet dau hoi cham thu nhat la cid
            ps.setString(4, "Thứ "+day +", kíp " + time); // xet dau hoi cham thu nhat la cid
            ps.setString(5, teacher_name); // xet dau hoi cham thu nhat la cid
            ps.setString(6, teacher_id); // xet dau hoi cham thu nhat la cid
            ps.setString(7, room); // xet dau hoi cham thu nhat la cid
            ps.setString(8, quantity_student);
            ps.setString(9, quantity_student);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
     public void DeleteGroup(String id) {
        String query = "delete from Groups where group_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // xet dau hoi cham thu nhat la cid
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
     public void EditGroup(String name_group, String course_id, String day, String time,String teacher_name, String teacher_id, String room, String quantity_student){
        String query = "update Groups set group_name = ? , course_id = ? , time = ? , teacher_name = ? ,  teacher_id = ? , room=? , max_students = ? , available_slots = ? where group_id = ?";
        //id
        String id = course_id+"_"+String.format("N%02d", Integer.parseInt(name_group.substring(5)));
//        String id = "1";
        // thoi gian
        String thoigian = "Thứ "+day +", kíp" + time;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
             // xet dau hoi cham thu nhat la cid
            ps.setString(1, name_group); // xet dau hoi cham thu nhat la cid
            ps.setString(2, course_id); // xet dau hoi cham thu nhat la cid
            ps.setString(3, "Thứ "+day +", kíp " + time); // xet dau hoi cham thu nhat la cid
            ps.setString(4, teacher_name); // xet dau hoi cham thu nhat la cid
            ps.setString(5, teacher_id); // xet dau hoi cham thu nhat la cid
            ps.setString(6, room); // xet dau hoi cham thu nhat la cid
            ps.setString(7, quantity_student);
            ps.setString(8, quantity_student);
            ps.setString(9, id);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List<Group> getGroupBySearchName(String search){
        List<Group> list = new ArrayList<>();
        String query = """
                       SELECT G.*
                       FROM Groups G
                       JOIN Courses C ON G.course_id = C.course_id
                       WHERE
                           G.group_id LIKE ?
                           OR G.group_name LIKE ?
                           OR G.course_id LIKE ?
                           OR G.[time] LIKE ?
                           OR G.teacher_name LIKE ?
                           OR G.room LIKE ?
                           OR C.course_name LIKE ?
                           OR C.num_credit LIKE ?
                           OR C.term LIKE ?;""";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setString(1, "%"+search+"%");
            ps.setString(2, "%"+search+"%");
            ps.setString(3, "%"+search+"%");
            ps.setString(4, "%"+search+"%");
            ps.setString(5, "%"+search+"%");
            ps.setString(6, "%"+search+"%");
            ps.setString(7, "%"+search+"%");
            ps.setString(8, "%"+search+"%");
            ps.setString(9, "%"+search+"%");
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Group(rs.getString(1),
                        rs.getString(2),
                        new Course(rs.getString(3)),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)));
            }
        } catch (Exception e) {
        }
        for (Group x : list) {
            x.setCourse(getCourse(x.getCourse().getId()));
        }
        return list;
    }
    
    public void editCourseRegister(String maSv,String maMon,double diem){
        String query = """
                       update CourseRegistrations
                       set grade = ?
                       where account_id = ? and course_id = ?
                       """;
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
             // xet dau hoi cham thu nhat la cid
            ps.setString(2, maSv); // xet dau hoi cham thu nhat la cid
            ps.setString(3, maMon); // xet dau hoi cham thu nhat la cid
            ps.setDouble(1, diem);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public CoursesRegistration getCoursesRegistrationByGroupId(String groupId,String maSv,double diem){
//        CoursesRegistration list = new CoursesRegistration();
        String query = """
                        select A.*,C.*,CR.term,CR.registration_date,CR.grade
                             from GroupRegistrations as GR
                             inner join Groups as G on G.group_id = GR.group_id
                             inner join Accounts as A on A.account_id = GR.account_id
                             inner join CourseRegistrations as CR on CR.account_id = A.account_id and CR.course_id = G.course_id
                             inner join Courses as C on C.course_id = CR.course_id
                             where G.group_id = ? and A.account_id = ?
                       """;
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setString(1, groupId);
            ps.setString(2,maSv);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                return new CoursesRegistration(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)), 
                        new Course(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10),rs.getInt(11)),
                        rs.getString(12),
                        rs.getDate(13),
                        diem);
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public List<CoursesRegistration> readExcel(String filePath, String idGroup) {
        List<CoursesRegistration> list =new ArrayList<>();
        try {
            String idCourse = idGroup.substring(0,7);
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
                if(dem < 10){
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
//                         System.out.print(String.format(Locale.US, "%.0f ", numericValue));
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
//                        new DAO().editCourseRegister(ma, idCourse, diem);
//                          list.add(new Grade(ma,idGroup,diem));
                          list.add(new DAO().getCoursesRegistrationByGroupId(idGroup, ma,diem));
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
        DAO dao = new DAO();
//        CoursesRegistration cr = dao.getCoursesRegistrationByGroupId("INT1319_N01","101");
        List<CoursesRegistration> list = dao.readExcel("C:\\Users\\ASUS\\Desktop\\Book2.xlsx", "INT1319_N01");
        for(CoursesRegistration x : list){
            System.out.println(x);
        }
//        System.out.println(cr);
//        dao.editCourseRegister("101", "BAS1105",2.5);
    }
}

package dao;

import context.DBContext;
import entity.Account;
import entity.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tieu_Dat
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

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

    public Account login(String user, String pass) {
        String query = "select * from Account\n"
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

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Course> list = dao.getAllCourse();
        for (Course o : list) {
            System.out.println(o);
        }
    }
}

package dao;

import context.DBContext;
import entity.Account;
import entity.Course;
import entity.Group;
import entity.GroupRegistration;
import jakarta.servlet.http.HttpSession;
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

    public List<Course> getCourseByStudentID(int id) {
        List<Course> list = new ArrayList<>();
        String query = "SELECT C.course_id, C.course_name, C.num_credit, C.term\n"
                + "FROM Courses AS C\n"
                + "JOIN CourseRegistrations AS CR\n"
                + "    ON C.course_id = CR.course_id\n"
                + "WHERE CR.account_id = ?;";
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

    public Group getGroupByID(String id) {
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

    public List<Group> getGroupByAccountID(int id) {
        List<Group> list = new ArrayList<>();
        String query = "SELECT G.*\n"
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
        String query = "select * from Courses where course_id = ? or course_name = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query); // chạy câu lệnh
            ps.setString(1, searchCourse);
            ps.setString(2, searchCourse);
            rs = ps.executeQuery(); // bảng kết quả
            while (rs.next()) {
                list.add(new Course(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }

        return list;

    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<GroupRegistration> list = dao.getGroupRegistrationByStudentID(101);
//        dao.DeleteCourse("BAS2003");
//        dao.EditCourse("Toán rời rạc", "INT1319", 10, 10);
        System.out.println(list.size());
        for (GroupRegistration o : list) {
            System.out.println(o);
        }
        Group a = dao.getGroupByID("INT1332_N01");
        System.out.println(a);
    }
}

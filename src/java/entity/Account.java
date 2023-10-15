package entity;

/**
 *
 * @author Tieu_Dat
 */
public class Account {
    private int id;
    private String name, username, password;
    private int isAdmin, isTeacher;

    public Account(int id, String name, String username, String password, int isAdmin, int isTeacher) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isTeacher = isTeacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(int isTeacher) {
        this.isTeacher = isTeacher;
    }
    
    
}

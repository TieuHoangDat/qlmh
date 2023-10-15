package entity;

/**
 *
 * @author Tieu_Dat
 */
public class Course {
    private String id, name;
    private int num_credit, term;

    public Course() {
    }
    
    
    public Course(String id, String name, int num_credit, int term) {
        this.id = id;
        this.name = name;
        this.num_credit = num_credit;
        this.term = term;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum_credit() {
        return num_credit;
    }

    public void setNum_credit(int num_credit) {
        this.num_credit = num_credit;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", num_credit=" + num_credit + ", term=" + term + '}';
    }
    
    
}

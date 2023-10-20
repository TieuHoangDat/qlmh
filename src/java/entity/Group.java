package entity;

/**
 *
 * @author Tieu_Dat
 */
public class Group {
    private String group_id, group_name;
    private Course course;
    private String time, teacher_name;
    private int teacher_id;
    private String room;
    private int max_students, available_slot;

    public Group(String group_id, String group_name, Course course, String time, String teacher_name, int teacher_id, String room, int max_students, int available_slot) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.course = course;
        this.time = time;
        this.teacher_name = teacher_name;
        this.teacher_id = teacher_id;
        this.room = room;
        this.max_students = max_students;
        this.available_slot = available_slot;
    }

    public Group(String group_id) {
        this.group_id = group_id;
    }

    public Group() {
    }
    
    

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getMax_students() {
        return max_students;
    }

    public void setMax_students(int max_students) {
        this.max_students = max_students;
    }

    public int getAvailable_slot() {
        return available_slot;
    }

    public void setAvailable_slot(int available_slot) {
        this.available_slot = available_slot;
    }

    @Override
    public String toString() {
        return "Group{" + "group_id=" + group_id + ", group_name=" + group_name + ", course=" + course + ", time=" + time + ", teacher_name=" + teacher_name + ", teacher_id=" + teacher_id + ", room=" + room + ", max_students=" + max_students + ", available_slot=" + available_slot + '}';
    }

   
    
    
}

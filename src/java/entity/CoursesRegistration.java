package entity;

import java.util.Date;

/**
 *
 * @author Tieu_Dat
 */
public class CoursesRegistration {
    private Account acccount;
    private Course course;
    private String term;
    private Date time;
    private double grade_10, grade_4;
    private String grade_alpha;

    public CoursesRegistration(Account acccount, Course course, String term, Date time, double grade_10) {
        this.acccount = acccount;
        this.course = course;
        this.term = term;
        this.time = time;
        
        this.grade_10 = Math.round(grade_10*10.0) / 10.0;
        if(grade_10 < 4) {
            grade_alpha = "F";
            grade_4 = 0;
        } 
        else if(grade_10 < 5) {
            grade_alpha = "D";
            grade_4 = 1;
        }  
        else if(grade_10 < 5.5) {
            grade_alpha = "D+";
            grade_4 = 1.5;
        }
        else if(grade_10 < 6.5) {
            grade_alpha = "C";
            grade_4 = 2;
        }
        else if(grade_10 < 7) {
            grade_alpha = "C+";
            grade_4 = 2.5;
        }
        else if(grade_10 < 8) {
            grade_alpha = "B";
            grade_4 = 3;
        }
        else if(grade_10 < 8.5) {
            grade_alpha = "B+";
            grade_4 = 3.5;
        }
        else if(grade_10 < 9) {
            grade_alpha = "A";
            grade_4 = 3.7;
        }
        else {
            grade_alpha = "A+";
            grade_4 = 4.0;
        }
        if(course.getNotcal()==1) {
            if(grade_10<4) {
                grade_alpha = "F";
            } else {
                grade_alpha = "P";
            }
        }
    }

    public Account getAcccount() {
        return acccount;
    }

    public void setAcccount(Account acccount) {
        this.acccount = acccount;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getGrade_10() {
        return grade_10;
    }

    public void setGrade_10(double grade_10) {
        this.grade_10 = grade_10;
    }

    public double getGrade_4() {
        return grade_4;
    }

    public void setGrade_4(double grade_4) {
        this.grade_4 = grade_4;
    }

    public String getGrade_alpha() {
        return grade_alpha;
    }

    public void setGrade_alpha(String grade_alpha) {
        this.grade_alpha = grade_alpha;
    }


    @Override
    public String toString() {
        return "CoursesRegistration{" + "acccount=" + acccount + ", course=" + course + ", term=" + term + ", time=" + time + ", grade_10=" + grade_10 + ", grade_4=" + grade_4 + ", grade_alpha=" + grade_alpha + '}';
    }
    
    
}

package entity;
import java.util.*;
/**
 *
 * @author Tieu_Dat
 */
public class Term implements Comparable<Term>{
    private String term, sx1, sx2;
    private List<CoursesRegistration> li;
    private double avg_10, avg_4, tl_10, tl_4;
    private int total_credit, tl_credit;

    public Term(String term, List<CoursesRegistration> li) {
        String s[] = term.trim().split("\\s+");
        sx1 = s[4];
        sx2 = s[1];
        this.term = term;
        this.li = li;
        int tmp = 0;
        double sum10 = 0, sum4 = 0;
        for(CoursesRegistration x : li) {
            if(x.getCourse().getNotcal() == 0) {
                sum10 += x.getGrade_10() * x.getCourse().getNum_credit();
                sum4 += x.getGrade_4() * x.getCourse().getNum_credit();
                tmp += x.getCourse().getNum_credit();
            }
        }
        total_credit = tmp;
        avg_10 = Math.round((sum10/tmp)*100.0) / 100.0;
        avg_4 = Math.round((sum4/tmp)*100.0) / 100.0;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<CoursesRegistration> getLi() {
        return li;
    }

    public void setLi(ArrayList<CoursesRegistration> li) {
        this.li = li;
    }

    public double getAvg_10() {
        return avg_10;
    }

    public void setAvg_10(double avg_10) {
        this.avg_10 = avg_10;
    }

    public double getAvg_4() {
        return avg_4;
    }

    public void setAvg_4(double avg_4) {
        this.avg_4 = avg_4;
    }

    public int getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(int total_credit) {
        this.total_credit = total_credit;
    }

    public double getTl_10() {
        return tl_10;
    }

    public void setTl_10(double tl_10) {
        this.tl_10 = tl_10;
    }

    public double getTl_4() {
        return tl_4;
    }

    public void setTl_4(double tl_4) {
        this.tl_4 = tl_4;
    }

    public int getTl_credit() {
        return tl_credit;
    }

    public void setTl_credit(int tl_credit) {
        this.tl_credit = tl_credit;
    }

    @Override
    public int compareTo(Term o) {
        if(!sx1.equals(o.sx1)) return sx1.compareTo(o.sx1);
        return sx2.compareTo(o.sx2);
    }
    
    
}

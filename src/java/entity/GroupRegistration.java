package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tieu_Dat
 */
public class GroupRegistration {
    private Account account;
    private Group group;
    private Date time;

    public GroupRegistration(Account account, Group group, Date time) {
        this.account = account;
        this.group = group;
        this.time = time;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getTime() {
        return time;
    }

    

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GroupRegistration{" + "account=" + account + ", group=" + group + ", time=" + time + '}';
    }
    
    
    
}

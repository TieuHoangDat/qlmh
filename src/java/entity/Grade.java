/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ASUS
 */
public class Grade {
    String ma,ten;
    double diem;

    public Grade(String ma, String ten, double diem) {
        this.ma = ma;
        this.ten = ten;
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "Grade{" + "ma=" + ma + ", ten=" + ten + ", diem=" + diem + '}';
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public double getDiem() {
        return diem;
    }
    
    
}

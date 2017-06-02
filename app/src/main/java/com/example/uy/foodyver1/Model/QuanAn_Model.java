package com.example.uy.foodyver1.Model;

/**
 * Created by UY on 5/8/2017.
 */

public class QuanAn_Model {
    public int ID;
    public String Ten;
    public int IDQH;
    public int IDDM;
    public String HinhAnh;
    public String TenQuan;
    public String Diem;

    public QuanAn_Model(int ID, String ten, int IDQH, int IDDM, String hinhAnh, String tenQuan, String diem) {
        this.ID = ID;
        Ten = ten;
        this.IDQH = IDQH;
        this.IDDM = IDDM;
        HinhAnh = hinhAnh;
        TenQuan = tenQuan;
        Diem = diem;
    }
}

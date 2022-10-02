package test;

import dao.SanPhamDAOImpl;
import dao.TaiKhoanDAOImpl;

public class Main {
    public static void main(String[] args) {
        SanPhamDAOImpl sp = new SanPhamDAOImpl();
        TaiKhoanDAOImpl tk = new TaiKhoanDAOImpl();

        System.out.println(tk.kiemTraTaiKhoan("admin"));
    }
}

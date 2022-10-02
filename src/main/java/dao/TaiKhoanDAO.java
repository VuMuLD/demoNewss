package dao;

import model.TaiKhoan;

public interface TaiKhoanDAO {

    //kiem tra xem tai khoan ton tai chua
    public boolean kiemTraTaiKhoan(String ten_dang_nhap);

    //them moi tai khoan
    public void themTaiKhoan(TaiKhoan tk);

    //kiem tra dang nhap
    public boolean kiemTraDangNhap(String ten_dang_nhap, String mat_khau);

    public TaiKhoan getTaiKhoan(String ten_dang_nhap);
}

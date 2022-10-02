package dao;

import model.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoanDatabase {
    Connection con;

    public TaiKhoanDatabase(Connection con) {
        this.con = con;
    }


    //log user
    public TaiKhoan logTaiKhoan(String Ten_dang_nhap, String Mat_khau) {
        TaiKhoan taiKhoan = null;

        try {
            String query = "select * from tai_khoan where ten_dang_nhap=? and mat_khau=?";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, taiKhoan.getTen_dang_nhap());
            ps.setString(2, taiKhoan.getMat_khau());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                taiKhoan = new TaiKhoan();
                taiKhoan.setMa_tai_khoan(rs.getString("ma_tai_khoan"));
                taiKhoan.setTen_tai_khoan(rs.getString("ten_tai_khoan"));
                taiKhoan.setTen_dang_nhap(rs.getString("ten_dang_nhap"));
                taiKhoan.setMat_khau(rs.getString("mat_khau"));
                taiKhoan.setQuyen_truy_cap(rs.getInt("quyen_truy_cap"));
                taiKhoan.setTinh_trang(rs.getInt("tinh_trang"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }
}

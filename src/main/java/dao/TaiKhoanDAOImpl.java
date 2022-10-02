package dao;

import Context.DBContext;
import model.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAOImpl implements TaiKhoanDAO{


    @Override
    public boolean kiemTraTaiKhoan(String ten_dang_nhap) {

        TaiKhoan tk = new TaiKhoan(ten_dang_nhap);
        if(tk==null)
            return true;
        return false;
    }

    @Override
    public void themTaiKhoan(TaiKhoan tk) {
        Connection cons = DBContext.getInstance().getConnection();
        String sql = "insert into tai_khoan value (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, tk.getMa_tai_khoan());
            ps.setString(2, tk.getTen_tai_khoan());
            ps.setString(3, tk.getTen_dang_nhap());
            ps.setString(4, tk.getMat_khau());
            ps.setInt(5, tk.getQuyen_truy_cap());
            ps.setInt(6, tk.getTinh_trang());
            ps.executeUpdate();
            cons.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public boolean kiemTraDangNhap(String ten_dang_nhap, String mat_khau) {

        TaiKhoan tk = new TaiKhoan(ten_dang_nhap, mat_khau);
        if(tk==null)
            return false;
        return true;
    }

    @Override
    public TaiKhoan getTaiKhoan(String ten_dang_nhap) {
        TaiKhoan taiKhoan = null;
        try {
            Connection cons = DBContext.getInstance().getConnection();
            String sql = "select * from tai_khoan where ten_dang_nhap=?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTen_dang_nhap());
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

    public TaiKhoan logTaiKhoan(String Ten_dang_nhap, String Mat_khau) {
        TaiKhoan taiKhoan = null;

        try {
            Connection cons = DBContext.getInstance().getConnection();
            String sql = "select * from tai_khoan where ten_dang_nhap=? and mat_khau=?";
            PreparedStatement ps = cons.prepareStatement(sql);
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

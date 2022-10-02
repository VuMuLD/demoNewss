package dao;

import Context.DBContext;
import model.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonDAOImpl implements HoaDonDAO{
    @Override
    public void themHoaDon(HoaDon hd) {
            Connection cons = DBContext.getInstance().getConnection();
            String sql = "insert into hoa_don value (?,?,?,?,?,?)";
            try {
                PreparedStatement ps = cons.prepareStatement(sql);
                ps.setString(1, hd.getMa_hoa_don());
                ps.setString(2, hd.getMa_tai_khoan().getMa_tai_khoan());
                ps.setString(3, hd.getDia_chi_giao_hang());
                ps.setString(4, hd.getPhuong_thuc_thanh_toan());
                ps.setTimestamp(5, hd.getNgay_mua_hang());
                ps.setInt(6, hd.getTinh_trang_don_hang());
                ps.executeUpdate();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

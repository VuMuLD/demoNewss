package dao;

import Context.DBContext;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietHoaDonDAOImpl implements ChiTietHoaDonDAO {
    @Override
    public void themCHiTietHD(ChiTietHoaDon cthd) {
            Connection cons = DBContext.getInstance().getConnection();
            String sql = "insert into chi_tiet_hoa_don value (?,?,?,?,?,?)";
            try {
                PreparedStatement ps = cons.prepareStatement(sql);
                ps.setInt(1, cthd.getMa_chi_tiet_hoa_don());
                ps.setString(2, cthd.getHoa_don().getMa_hoa_don());;
                ps.setInt(3, cthd.getSan_pham().getMa_san_pham());
                ps.setInt(4, cthd.getSo_luong());
                ps.setInt(5, cthd.getDon_gia());
                ps.setInt(6, cthd.getGiam_gia());
                ps.executeUpdate();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



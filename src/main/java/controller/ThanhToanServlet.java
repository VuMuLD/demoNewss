package controller;

import dao.ChiTietHoaDonDAOImpl;
import dao.HoaDonDAOImpl;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "ThanhToanServlet", value = "/ThanhToanServlet")
public class ThanhToanServlet extends HttpServlet {
    private HoaDonDAOImpl hdDAO = new HoaDonDAOImpl();
    private ChiTietHoaDonDAOImpl cthdDAO = new ChiTietHoaDonDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String dia_chi_giao_hang = request.getParameter("dia_chi_giao_hang");
        String phuong_thuc_thanh_toan = request.getParameter("phuong_thuc_thanh_toan");
        String tai_khoan = request.getParameter("tai_khoan");
        HttpSession session = request.getSession();
        GioHang cart = (GioHang) session.getAttribute("cart");

        try {
            Date date = new Date();
            String mhd = "" + date.getTime();
            TaiKhoan tk = new TaiKhoan();
            tk.setMa_tai_khoan(tai_khoan);
            HoaDon hd = new HoaDon(mhd, tk, dia_chi_giao_hang, phuong_thuc_thanh_toan, new Timestamp(new Date().getTime()), 0);
            hd.setMa_hoa_don(mhd);
            hdDAO.themHoaDon(hd);
            TreeMap<SanPham, Integer> list = cart.getList();
            for (Map.Entry<SanPham, Integer> ds : list.entrySet()){
                SanPham sp = new SanPham();
                sp.setMa_san_pham(ds.getKey().getMa_san_pham());
                cthdDAO.themCHiTietHD(new ChiTietHoaDon(0, hd, sp, ds.getValue(), ds.getKey().getDon_gia(), ds.getKey().getGiam_gia()));

            }
            response.sendRedirect("/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

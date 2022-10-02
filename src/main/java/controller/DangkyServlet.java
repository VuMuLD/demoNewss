package controller;

import dao.TaiKhoanDAOImpl;
import model.TaiKhoan;
import tools.MaHoa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "DangkyServlet", value = "/DangkyServlet")
public class DangkyServlet extends HttpServlet {

    private TaiKhoanDAOImpl taiKhoanDAO = new TaiKhoanDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String ten_tai_khoan = request.getParameter("ten_tai_khoan");
        String ten_dang_nhap = request.getParameter("ten_dang_nhap");
        String mat_khau = request.getParameter("mat_khau");

        String ten_tai_khoan_err = "";
        String ten_dang_nhap_err = "";
        String mat_khau_err = "";
        ///////////////////////////////////////////////
        if (ten_tai_khoan.equals("")) {
            ten_tai_khoan_err = "Vui lòng nhập họ và tên của bạn !";
        }
        if (ten_tai_khoan_err.length() > 0) {
            request.setAttribute("ten_tai_khoan_err", ten_tai_khoan_err);
        }

        ///////////////////////////////////////////////
        if (ten_dang_nhap.equals("")) {
            ten_dang_nhap_err = "Vui lòng nhập email của bạn !";
        } else if (taiKhoanDAO.kiemTraTaiKhoan(ten_dang_nhap) == true) {
            ten_dang_nhap_err = "Địa chỉ email đã đươc đăng ký !";
        }
        if (ten_dang_nhap.length() > 0) {
            request.setAttribute("ten_dang_nhap_err", ten_dang_nhap_err);
        }

        ///////////////////////////////////////////////
        if (mat_khau.equals("")) {
            mat_khau_err += "Vui lòng nhập mật khẩu của bạn !";
        }
        if (mat_khau_err.length() > 0) {
            request.setAttribute("mat_khau_err", mat_khau_err);
        }

        request.setAttribute("ten_tai_khoan", ten_tai_khoan);
        request.setAttribute("ten_dang_nhap", ten_dang_nhap);
        request.setAttribute("mat_khau", mat_khau);

        String url = "/account.jsp";
        try {
            if (ten_tai_khoan_err.length() == 0 && ten_dang_nhap_err.length() == 0 && mat_khau_err.length() == 0) {
                Date id = new Date();
                TaiKhoan tk = new TaiKhoan("" + id.getTime(), ten_tai_khoan, ten_dang_nhap, mat_khau, 1, 1);
                taiKhoanDAO.themTaiKhoan(tk);
                url = "/index.jsp";
            } else {
                url = "/account.jsp";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

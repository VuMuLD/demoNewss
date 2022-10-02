package controller;

import dao.TaiKhoanDAOImpl;
import tools.MaHoa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DangNhapServlet", value = "/DangNhapServlet")
public class DangNhapServlet extends HttpServlet {
    private TaiKhoanDAOImpl taiKhoanDAO = new TaiKhoanDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String ten_dang_nhap = request.getParameter("ten_dang_nhap");
        String mat_khau = request.getParameter("mat_khau");

        String err = "";
        if (ten_dang_nhap.equals("")){
            err = "Vui lòng nhập Email đăng nhập của bạn";
        }else if (taiKhoanDAO.kiemTraDangNhap(ten_dang_nhap, mat_khau) == false){
            err = "Email hoac mat khau khong chinh xac";

        }
        if (err.length() > 0){
            request.setAttribute("err", err);
        }

        request.setAttribute("ten_dang_nhap", ten_dang_nhap);
        request.setAttribute("mat_khau", mat_khau);
        String url = "/account.jsp";
        try {
            if (err.length() == 0){
                HttpSession session = request.getSession();
                session.setAttribute("username", ten_dang_nhap);
                url= "/index.jsp";
            }else {
                url = "/account.jsp";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception e) {

        }
    }
}

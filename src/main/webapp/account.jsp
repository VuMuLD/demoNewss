<%--
  Created by IntelliJ IDEA.
  User: vulm
  Date: 9/28/2022
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng Nhập & Đăng Ký</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">

    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</head>
<body>
<%
    String ten_tai_khoan_err = "", ten_dang_nhap_err = "", mat_khau_err= "";
    if (request.getAttribute("ten_tai_khoan_err") != null){
        ten_tai_khoan_err = (String) request.getAttribute("ten_tai-khoan_err");
    }
    if (request.getAttribute("ten_dang_nhap_err") != null){
        ten_dang_nhap_err = (String) request.getAttribute("ten_dang_nhap_err");
    }
    if (request.getAttribute("mat_khau_err") != null){
        mat_khau_err = (String) request.getAttribute("mat_khau_err");
    }
    ////////////////////////////////////////////////////////
    String ten_tai_khoan = "", ten_dang_nhap = "", mat_khau= "";
    if (request.getAttribute("ten_tai_khoan") != null){
        ten_tai_khoan = (String) request.getAttribute("ten_tai-khoan");
    }
    if (request.getAttribute("ten_dang_nhap") != null){
        ten_dang_nhap = (String) request.getAttribute("ten_dang_nhap");
    }
    if (request.getAttribute("mat_khau") != null){
        mat_khau = (String) request.getAttribute("mat_khau");
    }

    String err = "";
    if (request.getAttribute("err") != null){
        err = (String) request.getAttribute("err");
    }
%>

<jsp:include page="header.jsp"></jsp:include>

<section id="form"><!--form-->
    <div class="container">
        <div class="row">
            <div class="col-sm-4 col-sm-offset-1">
                <div class="login-form"><!--login form-->
                    <h2>Đăng Nhập</h2>
                    <form action="DangNhapServlet" method="post">
                        <p style="color:red"><%=err%></p>
                        <input type="email" placeholder="Địa Chỉ Email Đăng Nhập" name="ten_dang_nhap"/>
                        <p style="color:red"><%=mat_khau_err%></p>
                        <input type="password" placeholder="Mật Khẩu" name="mat_khau"/>
                        <span>
								<input type="checkbox" class="checkbox">
								Ghi nhớ thông tin
							</span>
                        <button type="submit" class="btn btn-default">Đăng nhập</button>
                    </form>
                </div><!--/login form-->
            </div>
            <div class="col-sm-1">
                <h2 class="or">Hoặc</h2>
            </div>
            <div class="col-sm-4">
                <div class="signup-form"><!--sign up form-->
                    <h2>Tài Khoản Mới</h2>
                    <form action="DangkyServlet" method="post">

                        <p style="color:red"><%=ten_tai_khoan_err%></p>
                        <input type="text" placeholder="Họ Và Tên" name="ten_tai_khoan" value="<%=ten_tai_khoan%>"/>

                        <p style="color:red"><%=ten_dang_nhap_err%></p>
                        <input type="email" placeholder="Địa Chỉ Email Đăng ký" name="ten_dang_nhap" value="<%=ten_dang_nhap%>"/>

                        <p style="color:red"><%=mat_khau_err%></p>
                        <input type="password" placeholder="Mật Khẩu" name="mat_khau" value="<%=mat_khau%>"/>

                        <button type="submit" class="btn btn-default">Đăng Ký</button>
                    </form>
                </div><!--/sign up form-->
            </div>
        </div>
    </div>
</section><!--/form-->


<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

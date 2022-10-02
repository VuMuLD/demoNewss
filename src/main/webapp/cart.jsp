<%@ page import="model.GioHang" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="model.SanPham" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: vulm
  Date: 9/28/2022
  Time: 8:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
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
    if (session.getAttribute("username") == null) {
        response.sendRedirect("account.jsp");
    }else {
        GioHang cart = (GioHang) session.getAttribute("cart");
        if (cart == null) {
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }
        TreeMap<SanPham, Integer> list = cart.getList();

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(0);
%>


<jsp:include page="header.jsp"></jsp:include>
<section>
    <section id="cart_items">
        <div class="container">
            <div class="breadcrumbs">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Home</a></li>
                    <li class="active">Shopping Cart</li>
                </ol>
            </div>
            <div class="table-responsive cart_info">
                <table class="table table-condensed">
                    <thead>
                    <tr class="cart_menu">
                        <td class="image">Sản Phẩm </td>
                        <td class="description"></td>
                        <td class="price">Giá Tiền</td>
                        <td class="quantity">Số lượng</td>
                        <td class="total">Tổng tiền</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Map.Entry<SanPham, Integer> ds : list.entrySet()) {
                    %>
                    <tr>
                        <td class="cart_product">
                            <a href="">
                                <img src="<%=ds.getKey().getHinh_anh()%>" alt="" width="110" height="110">
                            </a>
                        </td>

                        <td class=" cart_description">
                            <h4><a href=""><%=ds.getKey().getTen_san_pham()%>
                            </a></h4>
                            <p>Mã Sản Phẩm: <%=ds.getKey().getMa_san_pham()%>
                            </p>
                        </td>


                        <td class="cart_price">
                            <p><%=nf.format(ds.getKey().getDon_gia())%> USD</p>
                        </td>
                        <td class="cart_quantity">
                            <div class="cart_quantity_button">
                                <a class="cart_quantity_up"
                                   href="GioHangServlet?command=plus&ma_san_pham=<%=ds.getKey().getMa_san_pham()%>&cartID=<%=System.currentTimeMillis()%>">
                                    + </a>
                                <input class="cart_quantity_input" type="text" value="<%=ds.getValue()%>"
                                       autocomplete="off" size="2" disabled="">
                                <a class="cart_quantity_down"
                                   href="GioHangServlet?command=sub&ma_san_pham=<%=ds.getKey().getMa_san_pham()%>&cartID=<%=System.currentTimeMillis()%>">
                                    - </a>
                            </div>
                        </td>
                        <td class="cart_total">
                            <p class="cart_total_price"><%=nf.format(ds.getValue() * ds.getKey().getDon_gia())%> USD</p>
                        </td>
                        <td class="cart_delete">
                            <a class="cart_quantity_delete" href="GioHangServlet?command=remove&ma_san_pham=<%=ds.getKey().getMa_san_pham()%>&cartID=<%=System.currentTimeMillis()%>"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                        <td colspan="2">
                            <table class="table table-condensed total-result">
                                <tr>
                                    <td>Cart Sub Total</td>
                                    <td>$59</td>
                                </tr>
                                <tr>
                                    <td>Exo Tax</td>
                                    <td>$2</td>
                                </tr>
                                <tr class="shipping-cost">
                                    <td>Shipping Cost</td>
                                    <td>Free</td>
                                </tr>
                                <tr>
                                    <td>Total</td>
                                    <td><span>$61</span></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <a class="btn right btn-default update" href="">Hủy Đơn Hàng </a>
            </div>
        </div>
    </section> <!--/#cart_items-->

    <div class="container">
        <div class="step-one">
            <h2 class="heading">Xác Nhập Mua Hàng</h2>
        </div>
        <div class="shopper-informations">
            <div class="row">
                <div class="col-sm-3">
                    <div class="shopper-info">
                        <p>Xác nhận thanh toán</p>
                        <form action="ThanhToanServlet" method="post">
                            <input type="text" placeholder="Số điện thoại">
                            <p>Địa chỉ giao hàng</p>
                            <input type="text" placeholder="Note Địa Chỉ Gia Hàng " name="dia_chi_giao_hang" rows="5"/>

                            <p>Phương thức thanh toán:</p>
<%--                            <select name="phuong_thuc_thanh_toan">--%>
<%--                                <option value="1">Thanh Toán khi giao hàng</option>--%>
<%--                                <option value="2">Chuyển khoản ngân hàng</option>--%>
<%--                            </select>--%>
                            <input type="text" placeholder="Thanh Toán khi giao hàng or (Chuyển khoản ngân hàng) " name="phuong_thuc_thanh_toan" rows="3"/>
                            <input type="hidden" value="<%=session.getAttribute("username")%>"/>
                            <button type="submit" class="btn btn-default">Xác nhận thanh toán</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!--/#do_action-->
</section>
<%
    }
%>

<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>
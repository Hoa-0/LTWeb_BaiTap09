<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Trang chủ - Demo4</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">
    <h2 class="mb-3">Danh sách sản phẩm</h2>

    <!-- Thông báo -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Bảng sản phẩm -->
    <table class="table table-bordered">
        <thead class="table-light">
        <tr>
            <th>ID</th><th>Tên sản phẩm</th><th>Số lượng</th><th>Giá</th><th>Mô tả</th><th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.quantity}</td>
                <td>${p.price}</td>
                <td>${p.description}</td>
                <td>
                    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <a href="/products/edit/${p.id}" class="btn btn-sm btn-warning">Sửa</a>
                        <a href="/products/delete/${p.id}" class="btn btn-sm btn-danger"
                           onclick="return confirm('Xóa sản phẩm này?')">Xóa</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Nếu là admin thì cho phép thêm sản phẩm -->
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <a href="/products/new" class="btn btn-primary">+ Thêm sản phẩm</a>
    </c:if>

    <hr>
    <c:if test="${pageContext.request.userPrincipal != null}">
        <p>Xin chào, <b>${pageContext.request.userPrincipal.name}</b> |
            <a href="/logout">Đăng xuất</a></p>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal == null}">
        <p><a href="/login">Đăng nhập</a> | <a href="/register">Đăng ký</a></p>
    </c:if>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">
    <h2>Đăng nhập</h2>

    <c:if test="${param.error != null}">
        <div class="alert alert-danger">Sai tên đăng nhập hoặc mật khẩu.</div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">Bạn đã đăng xuất thành công.</div>
    </c:if>

    <form method="post" action="/login">
        <div class="mb-3">
            <label>Tên đăng nhập:</label>
            <input type="text" name="username" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Mật khẩu:</label>
            <input type="password" name="password" class="form-control" required/>
        </div>
        <button type="submit" class="btn btn-primary">Đăng nhập</button>
        <a href="/register" class="btn btn-link">Đăng ký</a>
    </form>
</body>
</html>

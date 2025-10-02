<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">
    <h2>Thêm sản phẩm</h2>

    <form method="post" action="/products/save">
        <div class="mb-3">
            <label>Tên sản phẩm:</label>
            <input type="text" name="name" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Số lượng:</label>
            <input type="number" name="quantity" class="form-control"/>
        </div>
        <div class="mb-3">
            <label>Giá:</label>
            <input type="number" step="0.01" name="price" class="form-control"/>
        </div>
        <div class="mb-3">
            <label>Mô tả:</label>
            <textarea name="description" class="form-control"></textarea>
        </div>
        <button type="submit" class="btn btn-success">Lưu</button>
        <a href="/index" class="btn btn-secondary">Hủy</a>
    </form>
</body>
</html>

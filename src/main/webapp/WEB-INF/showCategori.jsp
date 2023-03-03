<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- YOUR own local CSS -->
    <link rel="stylesheet" href="/css/style.css"/>
    <!-- For any Bootstrap that uses JS-->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>${category.name}</h1>
    <p><a href="/admin/dashboard">Home</a></p>
    <hr>

    <h3>You product In ${category.name} Categories</h3>
    <ul>
        <c:forEach var="product" items="${assingProduct}">
            <li><c:out value="${product.name}"></c:out></li>
        </c:forEach>
    </ul>
    <hr>
    <form action="/categories/${id}" method="post">
        <h4>Add Product:</h4>
        <select name="productId" id="productId" class="input">
            <c:forEach var="product" items="${unassingProduct}">
                <option value="${product.id}">${product.name}</option>
            </c:forEach>
        </select>
        <input class="input" class="button" type="submit" value="Add"/>
    </form>
</div>
</body>
</html>
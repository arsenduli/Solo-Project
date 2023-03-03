<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!-- c:out ; c:forEach ; c:if -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (like dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
    <!-- Bootstrap -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">

</head>

<body>
<div class="container">
    <!-- Beginning of Container -->
    <div
            class="d-flex flex-column justify-content-center align-items-center gap-2">
        <h1> Create Product:</h1>
        <a href="/admin/dashboard">Go Back</a>
    </div>
    <div
            class="d-flex justify-content-center gap-5 mt-3">
        <div class = "bg-light p-5">
            <%--@elvariable id="newAdmin" type="com"--%>
            <form class="form d-flex flex-column gap-3" action="/products/new"
                       method="post" enctype="multipart/form-data">
                <div
                        class=" d-flex flex-column justify-content-center align-items-center gap-2">
                    <label>Product Name: </label>
                    <input class="form-control" type="text" name="name"/>
                </div>
                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">
                    <label>Product Description: </label>
                    <input class="form-control" type="text" name="description">
                </div>
                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">
                    <label >Product Price: </label>
                    <input class="form-control" type="number" name="price">
                </div>
                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">
                    <label>Product Photos: </label>
                    <input class="form-control" type="file" name="pic"></input>
                </div>
                <input class="w-50 btn btn-primary" type="submit" value="Submit" />
            </form>
        </div>


    </div>
</div>
<!-- End of Container -->
</body>

</html>

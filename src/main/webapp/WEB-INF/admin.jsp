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
  <title>Register - Login Electro Admin</title>
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
    <h1>Seller Electro</h1>
  </div>
  <div
          class="d-flex justify-content-center gap-5 mt-3">
    <div class = "bg-light p-5">
      <h3>Register</h3>
      <%--@elvariable id="newAdmin" type="com"--%>
      <form:form class="form d-flex flex-column gap-3" action="/registerAdmin"
                 method="post" modelAttribute="newAdmin">
        <div
                class=" d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="nameAdmin" class="errors text-danger"></form:errors>
          <form:label path="nameAdmin">Name: </form:label>
          <form:input class="form-control" type="text" path="nameAdmin"></form:input>
        </div>
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">
          <form:errors path="emailAdmin" class="errors text-danger"></form:errors>
          <form:label path="emailAdmin">Email: </form:label>
          <form:input class="form-control" type="text" path="emailAdmin"></form:input>
        </div>
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">
          <form:errors path="passwordAdmin" class="errors text-danger"></form:errors>
          <form:label path="passwordAdmin">Password: </form:label>
          <form:input class="form-control" type="password" path="passwordAdmin"></form:input>
        </div>
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">
          <form:errors path="confirmPasswordAdmin" class="errors text-danger"></form:errors>
          <form:label path="confirmPasswordAdmin">Confirm Password: </form:label>
          <form:input class="form-control" type="password" path="confirmPasswordAdmin"></form:input>
        </div>
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">
          <form:errors path="tel" class="errors text-danger"></form:errors>
          <form:label path="tel">Number Phone: </form:label>
          <form:input class="form-control" type="tel" path="tel"></form:input>
        </div>
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">
          <form:errors path="country" class="errors text-danger"></form:errors>
          <form:label path="country">Country: </form:label>
          <form:input class="form-control" path="country"></form:input>
        </div>
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">
          <form:errors path="city" class="errors text-danger"></form:errors>
          <form:label path="city">City: </form:label>
          <form:input class="form-control" path="city"></form:input>
        </div>
        <input class="w-50 btn btn-primary" type="submit" value="Submit" />
      </form:form>
    </div>
    <div class = "bg-light p-5">
      <h3>Log In</h3>
      <%--@elvariable id="newLoginAdmin" type="com"--%>
      <form:form class="form d-flex flex-column gap-3" action="/loginAdmin"
                 method="post" modelAttribute="newLoginAdmin">
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">
          <form:errors path="emailAdmin" class="errors text-danger"></form:errors>
          <form:label path="emailAdmin">Email: </form:label>
          <form:input class="form-control" type="text" path="emailAdmin"></form:input>
        </div>
        <div
                class=" d-flex flex-column  justify-content-center align-items-center gap-2">

          <form:label path="passwordAdmin">Password: </form:label>
          <form:input class="form-control" type="password" path="passwordAdmin"></form:input>
          <form:errors path="passwordAdmin" class="errors text-danger"></form:errors>
        </div>
        <input class="w-50 btn btn-primary" type="submit" value="Submit" />
      </form:form>
    </div>
  </div>
</div>
<!-- End of Container -->
</body>

</html>





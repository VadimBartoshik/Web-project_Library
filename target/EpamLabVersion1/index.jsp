<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="language.jsp" %>

<!DOCTYPE html>
<style>
    <%@include file='css/style login.css' %>
</style>

<body>
<center>
    <p><img src="image/loginImage.jpg" alt="loginImage"></p>
</center>
<header>
    <jsp:include page="header.jsp"></jsp:include>
</header>

<form class="form-register" method="post" action="LoginController" name="LoginForm" onsubmit="return validate();">

    <h1><fmt:message key="login.Login"/></h1>

    <input type="text" name="txt_email" id="email" placeholder="<fmt:message key="login.EnterEmail"/>">

    <input type="password" name="txt_password" id="password" placeholder="<fmt:message key="login.EnterPassword"/>">

    <input type="submit" name="btn_login" value="<fmt:message key="login.Login"/>">

    <a href="register.jsp" class="form-log-in-with-existing"><fmt:message key="login.DoYouHaveAnAccount"/> <b>
        <fmt:message key="login.RegisterHere"/> </b></a>

</form>

</body>
</html>
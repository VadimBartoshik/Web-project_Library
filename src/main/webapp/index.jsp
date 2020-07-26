<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="src/main/resources/global" />
<!DOCTYPE html>
<html lang="${language}">
  <style>
       <%@include file='css/style login.css' %>
  </style>
  <head>
      <meta charset="utf-8">
      <title>Animated Login Form</title>
      <link rel="stylesheet" href="style login.css">
  </head>
      <body>
      <center>
          <p><img src="image/loginImage.jpg" alt="loginImage"></p>
      </center>

            <form class="form-register" method="post" action="LoginController" name="LoginForm" onsubmit="return validate();">

                <%--@declare id="username"--%><h1>Log In</h1>
                <label for="username"><fmt:message key="global.name" />:</label>
                <input type="text" name="txt_email" id="email" placeholder="enter email">

                <input type="password" name="txt_password" id="password" placeholder="enter password">

			    <input type="submit" name="btn_login" value="Login">

				<a href="register.jsp" class="form-log-in-with-existing">You Don^t have an account? <b> Register here </b></a>

            </form>

      <s:url var="indexEN" namespace="/" action="locale" >
          <s:param name="request_locale" >en</s:param>
      </s:url>
      <s:url var="indexES" namespace="/" action="locale" >
          <s:param name="request_locale" >es</s:param>
      </s:url>
      <s:url var="indexFR" namespace="/" action="locale" >
          <s:param name="request_locale" >fr</s:param>
      </s:url>
      <s:url var="indexRU" namespace="/" action="locale" >
          <s:param name="request_locale" >ru</s:param>
      </s:url>

      <s:a href="%{indexEN}" >English</s:a>
      <s:a href="%{indexES}" >Spanish</s:a>
      <s:a href="%{indexFR}" >France</s:a>
      <s:a href="%{indexRU}" >Russian</s:a>
      </body>
</html>
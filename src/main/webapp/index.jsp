<!DOCTYPE html>
<html lang="en" dir="ltr">
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

                <h1>Log In</h1>

                <input type="text" name="txt_email" id="email" placeholder="enter email">

                <input type="password" name="txt_password" id="password" placeholder="enter password">

			    <input type="submit" name="btn_login" value="Login">

				<a href="register.jsp" class="form-log-in-with-existing">You Don^t have an account? <b> Register here </b></a>

            </form>
      </body>
</html>
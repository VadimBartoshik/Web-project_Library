<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="language.jsp" %>
<jsp:include page="header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <style>
       <%@include file='css/style register.css' %>
  </style>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		
	<title>Register : onlyxscript.blogspot.com</title>
	
	<!-- javascript for registeration form validation-->
	<script>	
	
		function validate()
		{
			var first_name= /^[a-z A-Z]+$/; //pattern allowed alphabet a-z or A-Z 
			var last_name= /^[a-z A-Z]+$/; //pattern allowed alphabet a-z or A-Z 
			var email_valid= /^[\w\d\.]+\@[a-zA-Z\.]+\.[A-Za-z]{1,4}$/; //pattern valid email validation
			var password_valid=/^[A-Z a-z 0-9 !@#$%&*()<>]{6,12}$/; //pattern password allowed A to Z, a to z, 0-9, !@#$%&*()<> charecter 
			
			var fname = document.getElementById("fname"); //textbox id fname
            var lname = document.getElementById("lname"); //textbox id lname
            var email = document.getElementById("email"); //textbox id email
            var password = document.getElementById("password"); //textbox id password
			fname.style.background='none';
			lname.style.background='none';
			email.style.background='none';
			password.style.background='none';
			if(!first_name.test(fname.value) || fname.value=='') 
            {
				alert("Enter Firstname Alphabet Only....!");
                fname.focus();
                fname.style.background = '#f08080';
                return false;                    
            }
			if(!last_name.test(lname.value) || lname.value=='') 
            {
				alert("Enter Lastname Alphabet Only....!");
                lname.focus();
                lname.style.background = '#f08080';
                return false;                    
            }
			if(!email_valid.test(email.value) || email.value=='') 
            {
				alert("Enter Valid Email....!");
                email.focus();
                email.style.background = '#f08080';
                return false;                    
            }
			if(!password_valid.test(password.value) || password.value=='') 
            {
				alert("Password Must Be 6 to 12 and allowed !@#$%&*()<> character");
                password.focus();
                password.style.background = '#f08080';
                return false;                    
            }
		}
		
	</script>	

</head>

<body>
    <div class="main-content">
        <p><img src="image/registerImage.jpg" alt="register"></p>
        <form class="form-register" method="post" action="addUser" onsubmit="return validate();">

            <h1><fmt:message key="register.Register"/></h1>

            <input type="text" name="txt_firstName" id="fname" placeholder="<fmt:message key="register.EnterFirstName"/>">

            <input type="text" name="txt_lastName" id="lname" placeholder="<fmt:message key="register.EnterLastName"/>">

            <input type="text" name="txt_email" id="email" placeholder="<fmt:message key="register.EnterEmail"/>">

            <input type="password" name="txt_password" id="password" placeholder="<fmt:message key="register.EnterPassword"/>">

		    <input type="submit" name="btn_register" value="<fmt:message key="register.Register"/>">
				
            <a href="index.jsp" class="form-log-in-with-existing"><fmt:message key="register.AlreadyHaveAnAccount"/> <b> Login here </b></a>

        </form>

    </div>

</body>

</html>

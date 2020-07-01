
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
        <form class="form-register" method="post" action="RegisterController" onsubmit="return validate();">

            <h1>Register</h1>

            <input type="text" name="txt_firstName" id="fname" placeholder="enter firstname">

            <input type="text" name="txt_lastName" id="lname" placeholder="enter lastname">

            <input type="text" name="txt_email" id="email" placeholder="enter email">

            <input type="password" name="txt_password" id="password" placeholder="enter password">

		    <input type="submit" name="btn_register" value="Register">
				
            <a href="index.jsp" class="form-log-in-with-existing">Already have an account? <b> Login here </b></a>

        </form>

    </div>

</body>

</html>

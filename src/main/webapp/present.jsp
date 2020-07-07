
<!DOCTYPE html>
<html lang="ru" dir="ltr">
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<head>
    <meta charset="utf-8">
    <title>Present Book Form</title>
</head>
<body>
<style>
    <%@include file='css/style present.css' %>
</style>
<div class="main">
    <p><img src="image/presentBookImage.jpg" alt="presentBook"></p>
    <form class="presentBook-form" method="post" name="presentForm" action="PresentController" onsubmit="return validate();">

            <h1>Present book</h1>
        <input type="text" name="txt_title" placeholder="enter title">

        <input type="text" name="txt_author" placeholder="enter author">

        <input type="submit" name="btn_present" value="Present">

        <center>
            <h1><a class="back" href="welcome.jsp">Back</a></h1>
        </center>

    </form>

</div>


</body>
</html>
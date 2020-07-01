<body>
<style>
    <%@include file='css/style present.css' %>
</style>
<div class="main">
    <p><img src="image/presentBookImage.jpg" alt="presentBook"></p>
    <form class="presentBook-form" method="post" name="presentForm"  onsubmit="return validate();">

            <h1>Present book</h1>
        <input type="text" name="txt_title" placeholder="enter title">

        <input type="text" name="txt_author" placeholder="enter author">

        <input type="submit" name="btn_add" value="Present">

        <center>
            <h1><a href="welcome.jsp">Back</a></h1>
        </center>

    </form>

</div>


</body>
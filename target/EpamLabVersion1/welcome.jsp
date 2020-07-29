
<body>
<p><img src="image/welcomeImage.jpg" alt="welcome"></p>
<style>
    <%@include file='css/style welcome.css' %>
</style>
<header>
    <center>
        <h2>

            <%
                if (session.getAttribute("login") == null || session.getAttribute("login") == "") {
                    response.sendRedirect("index.jsp");
                }
            %>

            welcome, <%=session.getAttribute("login")%>

        </h2>
    </center>

    <div class="inner-width">
        <nav class="navigation-menu">
            <a href="getAll.jsp"> <img src="icon/getAllIcon.png"> get all</a>
            <a href="take.jsp"> <img src="icon/takeBookIcon.png"> Take book</a>
            <a href="return.jsp"> <img src="icon/returnIcon.png"> Return book</a>
            <a href="present.jsp"> <img src="icon/presentIcon.png"> Present book</a>
            <a href="buy.jsp"> <img src="icon/buyIcon.png"> Buy book</a>
            <a href="logout.jsp"> <img src="icon/logOutIcon.png">Logout</a>
        </nav>
    </div>
</header>
</body>





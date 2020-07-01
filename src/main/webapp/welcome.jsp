<body>
<style>
    <%@include file='css/style welcome.css' %>
</style>
<header>
    <center>
        <h2>

            <%
                if(session.getAttribute("login") ==null || session.getAttribute("login")=="")
                {
                    response.sendRedirect("index.jsp");
                }
            %>

            Welcome, <%=session.getAttribute("login")%>

        </h2>
    </center>
    <div class="inner-width">
        <a href="#" class="logo"> <img src="icon/libraryIcon.png"></a>
        <i class="menu-toggle-btn fas fa-bars"></i>
        <nav class="navigation-menu">
            <a href="#"> <img src="icon/takeBookIcon.png"> Take book</a>
            <a href="#"> <img src="icon/returnIcon.png"> Return book</a>
            <a href="#"> <img src="icon/presentIcon.png"> </i> Present book</a>
            <a href="#"> <img src="icon/buyIcon.png"> Buy book</a>
            <a href="logout.jsp"> <img src="icon/logOutIcon.png">Logout</a>
        </nav>
    </div>
</header>



</body>





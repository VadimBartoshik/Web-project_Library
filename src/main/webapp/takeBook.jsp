<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table border="1">
    <c:forEach items="${ requestScope.users}" var="user">
        <tr>
            <td><c:out value="${book.Id }" /></td>
            <td><c:out value="${book.title }" /></td>
            <td><c:out value="${book.author }" /></td>
            <td><c: value="${book.user }" /></td>
        </tr>
    </c:forEach>
</table>
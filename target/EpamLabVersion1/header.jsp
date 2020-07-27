
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="language.jsp" %>

<form action="LanguageController" method="post" style="display: inline; margin-left: 220px;">
    <input hidden name="command" value="lokalization">
    <button type="submit" name="language" value="ru_RU">RUS</button>
    <button type="submit" name="language" value="en_US">ENG</button>
</form>
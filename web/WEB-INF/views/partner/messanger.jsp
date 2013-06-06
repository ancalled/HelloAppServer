<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${param.mess eq 'file-err'}">
        <c:set var="type" value="alert alert-success"/>
        <c:set var="messange" value="Файл иконки не был загружен"/>
    </c:when>
    <c:when test="${param.mess eq 'camp-deleted'}">
        <c:set var="type" value="alert alert-success"/>
        <c:set var="messange" value="Компания ${param.camp} была удалена"/>
    </c:when>
</c:choose>

<c:if test="${not empty messange}">
    <div class=${type}>${messange}</div>
</c:if>


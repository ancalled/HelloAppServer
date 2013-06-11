<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${param.mess eq 'partner-deleted'}">
        <c:set var="type" value="alert alert-success"/>
        <c:set var="messange" value="Партнерская компания ${param.partner} была удалена"/>
    </c:when>
</c:choose>

<c:if test="${not empty messange}">
    <div class=${type}>${messange}</div>
</c:if>


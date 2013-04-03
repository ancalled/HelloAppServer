<%@ page import="com.mcsm.hellapp.model.domain.DiscountStat" %>
<%@ page import="com.mcsm.hellapp.model.service.HelloAppCatalog" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ancalled
  Date: 3/31/13
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HelloAppCatalog catalog = null;
    try {
        InitialContext context = new InitialContext();
        catalog = (HelloAppCatalog) context.lookup("java:app/HelloAppServer.jar/helloApp");
    } catch (NamingException e) {
        e.printStackTrace();
    }


    long discId = 1L;
    Date fromDate = new Date();
    Date toDate = new Date();
    List<DiscountStat> stats = catalog.getDiscounts(discId, fromDate, toDate);

%>
<html>
<head>
    <title>Client View</title>
</head>
<body>

<%
    if (stats != null) {
%>
<ul>
    <%
        for (DiscountStat stat : stats) {
    %>
    <li>
         <%=stat.getWhenApllied()%>
    </li>
    <%
        }
    %>
</ul>

<%
    }
%>

</body>
</html>
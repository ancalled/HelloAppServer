<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.mcsm.hellapp.model.service.HelloAppCatalog" %>
<%@ page import="javax.naming.NamingException" %>
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


%>
<html>
<head>
    <title>Client View</title>
</head>
<body>

    <ul>
        <li>

        </li>
    </ul>

</body>
</html>
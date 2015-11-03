<%-- 
    Document   : index
    Created on : Oct 26, 2015, 4:50:53 PM
    Author     : William
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Maintenance</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <h1>Product Maintenance</h1>
        <a href="<c:url value='/loadProducts' />">View Products</a>
    </body>
</html>

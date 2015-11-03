<%-- 
    Document   : index
    Created on : Oct 26, 2015, 4:50:53 PM
    Author     : William
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>        
        <c:import url='includes/header.html' />
    </head>
    <body>
        <h1>Product Maintenance</h1>
        <a href="<c:url value='/loadProducts' />">View Products</a>
    </body>
</html>
